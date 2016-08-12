package com.github.slamdev.boot.load.balancer.autoconfigure;

import com.github.slamdev.load.balancer.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.*;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import static java.net.URI.create;
import static org.springframework.http.HttpMethod.resolve;

@Configuration
@LoadBalanced
public class LoadBalancerClientHttpRequestFactory extends AbstractClientHttpRequestFactoryWrapper {

    private final LoadBalancer loadBalancer;

    @Autowired
    public LoadBalancerClientHttpRequestFactory(LoadBalancer loadBalancer, ClientHttpRequestFactory requestFactory) {
        super(requestFactory);
        this.loadBalancer = loadBalancer;
    }

    @Override
    protected ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod, ClientHttpRequestFactory requestFactory) {
        return new InterceptingClientHttpRequest(loadBalancer, requestFactory, uri, httpMethod);
    }

    private static class InterceptingClientHttpRequest extends AbstractClientHttpRequest {

        private final LoadBalancer loadBalancer;
        private final ClientHttpRequestFactory requestFactory;

        private final HttpMethod method;

        private final URI uri;

        private ByteArrayOutputStream bufferedOutput = new ByteArrayOutputStream(1024);

        InterceptingClientHttpRequest(LoadBalancer loadBalancer, ClientHttpRequestFactory requestFactory, URI uri, HttpMethod method) {
            this.loadBalancer = loadBalancer;
            this.requestFactory = requestFactory;
            this.method = method;
            this.uri = uri;
        }

        @Override
        public HttpMethod getMethod() {
            return method;
        }

        @Override
        public URI getURI() {
            return uri;
        }

        @Override
        protected OutputStream getBodyInternal(HttpHeaders headers) throws IOException {
            return bufferedOutput;
        }

        @Override
        protected ClientHttpResponse executeInternal(HttpHeaders headers) throws IOException {
            byte[] bytes = bufferedOutput.toByteArray();
            if (headers.getContentLength() < 0) {
                headers.setContentLength(bytes.length);
            }
            ClientHttpResponse result = executeInternal(bytes);
            bufferedOutput = null;
            return result;
        }

        private ClientHttpResponse executeInternal(byte[] body) throws IOException {
            return loadBalancer.executeRequest(getURI().toASCIIString(), getMethod().name(), (uri, method) -> {
                ClientHttpRequest delegate = requestFactory.createRequest(create(uri), resolve(method));
                delegate.getHeaders().putAll(getHeaders());
                if (body.length > 0) {
                    StreamUtils.copy(body, delegate.getBody());
                }
                return delegate.execute();
            });
        }
    }
}
