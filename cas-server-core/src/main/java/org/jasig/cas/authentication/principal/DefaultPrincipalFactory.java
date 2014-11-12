/*
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.cas.authentication.principal;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Factory to create {@link org.jasig.cas.authentication.principal.SimplePrincipal} objects.
 * @author Misagh Moayyed
 */
public final class DefaultPrincipalFactory implements PrincipalFactory {
    private static final long serialVersionUID = -3999695695604948495L;

    @NotNull
    private final PrincipalAttributesRepository attributesRepository;

    /**
     * Instantiates a new Default principal factory.
     */
    public DefaultPrincipalFactory() {
        this(new DefaultPrincipalAttributesRepository());
    }

    /**
     * Instantiates a new Default principal factory.
     *
     * @param attributesRepository the attribute repository
     */
    public DefaultPrincipalFactory(final PrincipalAttributesRepository attributesRepository) {
        this.attributesRepository = attributesRepository;
    }

    @Override
    public Principal createPrincipal(final String id) {
        return new SimplePrincipal(id, this.attributesRepository);
    }

    @Override
    public Principal createPrincipal(final String id, final Map<String, Object> attributes) {
        this.attributesRepository.setAttributes(id, attributes);
        return new SimplePrincipal(id, this.attributesRepository);
    }

    public PrincipalAttributesRepository getAttributesRepository() {
        return attributesRepository;
    }
}
