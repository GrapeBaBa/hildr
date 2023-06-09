/*
 * Copyright 2023 281165273grape@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.optimism.common;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.Response;

/**
 * The type RequestWrapper.
 *
 * @param <S> params type.
 * @param <T> result type.
 * @param <R> response type.
 * @author zhouxing
 * @since 0.1.0
 */
public class RequestWrapper<S, T, R extends Response<T>> {

  /** org.web3j.protocol.core.Request */
  private Request<S, R> request;

  /**
   * constructor.
   *
   * @param request org.web3j.protocol.core.Request.
   */
  public RequestWrapper(Request<S, R> request) {
    this.request = request;
  }

  /**
   * sent request by virtual thread.
   *
   * @return Future
   */
  @SuppressWarnings("preview")
  public CompletableFuture<R> sendVtAsync() {
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      return CompletableFuture.supplyAsync(
          () -> {
            try {
              return request.send();
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          },
          executor);
    }
  }
}
