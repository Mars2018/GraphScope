/**
 * Copyright 2020 Alibaba Group Holding Limited.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.compiler.demo.server.plan.strategy.shuffle;

import com.compiler.demo.server.plan.strategy.PropertyIdentityStep;
import org.apache.tinkerpop.gremlin.process.traversal.Step;
import org.apache.tinkerpop.gremlin.process.traversal.step.filter.HasStep;
import org.apache.tinkerpop.gremlin.process.traversal.step.map.PropertiesStep;
import org.apache.tinkerpop.gremlin.process.traversal.step.map.PropertyMapStep;
import org.apache.tinkerpop.gremlin.process.traversal.step.map.VertexStep;

public class ShuffleStrategy {
    public static boolean needShuffle(Step step) {
        if (step instanceof VertexStep) return true;
        if (step instanceof HasStep) {
            return new HasStepProperty((HasStep) step).needShuffle();
        } else if (step instanceof PropertyIdentityStep) {
            return new IdentityProperty(step).needShuffle();
        } else if (step instanceof PropertiesStep || step instanceof PropertyMapStep) {
            return new ValueProperty(step).needShuffle();
        } else {
            return false;
        }
    }
}
