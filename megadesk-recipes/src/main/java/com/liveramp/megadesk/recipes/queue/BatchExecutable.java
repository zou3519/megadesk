/**
 *  Copyright 2014 LiveRamp
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.liveramp.megadesk.recipes.queue;

import com.google.common.collect.ImmutableList;

import com.liveramp.megadesk.base.state.Local;
import com.liveramp.megadesk.base.transaction.BaseTransactionExecutor;
import com.liveramp.megadesk.core.transaction.TransactionExecutor;
import com.liveramp.megadesk.recipes.state.DriverFactory;

public class BatchExecutable<VALUE> extends BaseQueueExecutable<VALUE, ImmutableList<VALUE>> implements MegadeskPersistentBatch<VALUE> {

  public BatchExecutable(Batch<VALUE> batch, TransactionExecutor executor) {
    super(batch, executor);
  }

  public static <VALUE> BatchExecutable<VALUE> getBatchByName(String name,
                                                              DriverFactory<ImmutableList<VALUE>> listFactory,
                                                              DriverFactory<Boolean> boolFactory,
                                                              BaseTransactionExecutor executor) {
    return new BatchExecutable<VALUE>(
        new Batch<VALUE>(
            new Local<ImmutableList<VALUE>>(listFactory.get(name + "-input", ImmutableList.<VALUE>of())),
            new Local<ImmutableList<VALUE>>(listFactory.get(name + "-output", ImmutableList.<VALUE>of())),
            new Local<Boolean>(boolFactory.get(name + "-frozen", false))),
        executor
    );
  }
}
