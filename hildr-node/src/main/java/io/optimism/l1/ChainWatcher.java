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

package io.optimism.l1;

import io.optimism.config.Config;
import java.math.BigInteger;
import java.util.concurrent.Executors;
import org.jctools.queues.MpscBlockingConsumerArrayQueue;

/**
 * the ChainWatcher class.
 *
 * @author thinkAfCod
 * @since 0.1.0
 */
@SuppressWarnings({"UnusedVariable", "preview"})
public class ChainWatcher {

  private final InnerWatcher innerWatcher;

  /**
   * the ChainWatcher constructor.
   *
   * @param l1StartBlock the start block number of l1
   * @param l2StartBlock the start block number of l2
   * @param config the global config
   */
  public ChainWatcher(BigInteger l1StartBlock, BigInteger l2StartBlock, Config config) {
    this.innerWatcher =
        new InnerWatcher(
            config,
            new MpscBlockingConsumerArrayQueue<>(1000),
            l1StartBlock,
            l2StartBlock,
            Executors.newVirtualThreadPerTaskExecutor());
  }

  /** start ChainWatcher. */
  public void start() {
    innerWatcher.startAsync().awaitRunning();
  }

  /** stop the ChainWatcher. */
  public void stop() {
    innerWatcher.stopAsync();
    innerWatcher.awaitTerminated();
  }
}
