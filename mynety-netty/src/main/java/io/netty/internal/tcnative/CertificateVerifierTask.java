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


/**
 * Execute {@link CertificateVerifier#verify(long, byte[][], String)}.
 */
final class CertificateVerifierTask extends SSLTask {
  private final byte[][] x509;
  private final String authAlgorithm;
  private final CertificateVerifier verifier;

  CertificateVerifierTask(long ssl, byte[][] x509, String authAlgorithm, CertificateVerifier verifier) {
    super(ssl);
    this.x509 = x509;
    this.authAlgorithm = authAlgorithm;
    this.verifier = verifier;
  }

  @Override
  protected int runTask(long ssl) {
    return verifier.verify(ssl, x509, authAlgorithm);
  }
}
