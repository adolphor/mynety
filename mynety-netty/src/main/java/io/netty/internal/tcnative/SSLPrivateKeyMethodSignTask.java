/*
 * Copyright 2019 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.internal.tcnative;

final class SSLPrivateKeyMethodSignTask extends SSLPrivateKeyMethodTask {
  private final int signatureAlgorithm;
  private final byte[] digest;

  SSLPrivateKeyMethodSignTask(long ssl, int signatureAlgorithm, byte[] digest, SSLPrivateKeyMethod method) {
    super(ssl, method);
    this.signatureAlgorithm = signatureAlgorithm;
    // It's OK to not clone the arrays as we create these in JNI and not reuse.
    this.digest = digest;
  }

  @Override
  protected byte[] runTask(long ssl, SSLPrivateKeyMethod method) throws Exception {
    return method.sign(ssl, signatureAlgorithm, digest);
  }
}
