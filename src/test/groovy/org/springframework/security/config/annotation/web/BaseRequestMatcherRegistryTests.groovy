/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.config.annotation.web;

import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RegexRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import spock.lang.Specification;

/**
 * @author Rob Winch
 *
 */
class BaseRequestMatcherRegistryTests extends Specification {
    BaseRequestMatcherRegistryStub registry = new BaseRequestMatcherRegistryStub()

    def "regexMatchers(GET,'/a.*') uses RegexRequestMatcher"() {
        when:
        def matchers = registry.regexMatchers(HttpMethod.GET,"/a.*")
        then: 'matcher is a RegexRequestMatcher'
        matchers.collect {it.class } == [RegexRequestMatcher]
    }

    def "regexMatchers('/a.*') uses RegexRequestMatcher"() {
        when:
        def matchers = registry.regexMatchers("/a.*")
        then: 'matcher is a RegexRequestMatcher'
        matchers.collect {it.class } == [RegexRequestMatcher]
    }

    def "antMatchers(GET,'/a.*') uses AntPathRequestMatcher"() {
        when:
        def matchers = registry.antMatchers(HttpMethod.GET, "/a.*")
        then: 'matcher is a RegexRequestMatcher'
        matchers.collect {it.class } == [AntPathRequestMatcher]
    }

    def "antMatchers('/a.*') uses AntPathRequestMatcher"() {
        when:
        def matchers = registry.antMatchers("/a.*")
        then: 'matcher is a AntPathRequestMatcher'
        matchers.collect {it.class } == [AntPathRequestMatcher]
    }

    static class BaseRequestMatcherRegistryStub extends BaseRequestMatcherRegistry<List<RequestMatcher>,DefaultSecurityFilterChain,HttpConfigurator> {
        List<AccessDecisionVoter> decisionVoters() {
            return null;
        }

        List<RequestMatcher> chainRequestMatchers(List<RequestMatcher> requestMatchers) {
            return requestMatchers;
        }
    }
}
