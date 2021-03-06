/*
 * Copyright (c) 2010-2016 Osman Shoukry
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.openpojo.random.generator.security;

import java.util.Arrays;
import java.util.Collection;

import com.openpojo.random.RandomFactory;
import com.openpojo.random.RandomGenerator;
import sun.security.krb5.EncryptionKey;

/**
 * @author oshoukry
 */
public class EncryptionKeyRandomGenerator implements RandomGenerator {
  private static final Class<?>[] TYPES = new Class<?>[] { EncryptionKey.class };
  private static final RandomGenerator INSTANCE = new EncryptionKeyRandomGenerator();

  private EncryptionKeyRandomGenerator() {
  }

  public static RandomGenerator getInstance() {
    return INSTANCE;
  }

  public Collection<Class<?>> getTypes() {
    return Arrays.asList(TYPES);
  }

  public Object doGenerate(Class<?> type) {
    //noinspection ConstantConditions
    return new EncryptionKey(RandomFactory.getRandomValue(byte[].class),  RandomFactory.getRandomValue(Integer.class), null);
  }
}