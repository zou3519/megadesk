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

package com.liveramp.megadesk.transaction;

import java.util.Collection;

import com.liveramp.megadesk.state.Driver;

public class BaseTransaction implements Transaction {

  private BaseTransactionData data;
  private BaseTransactionExecution execution;

  public BaseTransaction(Collection<Driver> reads, Collection<Driver> writes) {
    BaseTransactionDependency dependency = new BaseTransactionDependency(reads, writes);
    data = new BaseTransactionData(dependency);
    execution = new BaseTransactionExecution(dependency, data);
  }

  @Override
  public TransactionData data() {
    return data;
  }

  @Override
  public TransactionExecution execution() {
    return execution;
  }
}
