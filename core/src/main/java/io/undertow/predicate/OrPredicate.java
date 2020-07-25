/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.undertow.predicate;

import io.undertow.server.HttpServerExchange;

/**
 * @author Stuart Douglas
 */
class OrPredicate implements Predicate {

    private final Predicate[] predicates;

    OrPredicate(final Predicate... predicates) {
        this.predicates = predicates;
    }

    @Override
    public boolean resolve(final HttpServerExchange value) {
        for (final Predicate predicate : predicates) {
            if (predicate.resolve(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "";
        for(final Predicate predicate : predicates) {
            if( result.length() > 0 ) {
                result += " or ";
            }
            result += predicate.toString();
        }
        return result;
    }
}
