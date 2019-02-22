/*
 * Copyright 2017 Apereo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tle.core.dao.helpers;

import java.util.Iterator;
import org.hibernate.ScrollableResults;

/**
 * Use for forward only scrollable results.
 *
 * @author Aaron
 */
public class ScrollableResultsIterator<T> implements Iterator<T> {
  private final ScrollableResults results;
  private int index = -1;

  public ScrollableResultsIterator(ScrollableResults results) {
    this.results = results;
  }

  @Override
  public boolean hasNext() {
    return results.next();
  }

  @SuppressWarnings("unchecked")
  @Override
  public T next() {
    index++;
    return (T) results.get(0);
  }

  @Override
  public void remove() {
    // No
  }

  public int getIndex() {
    return index;
  }
}
