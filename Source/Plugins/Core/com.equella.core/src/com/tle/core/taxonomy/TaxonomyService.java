/*
 * Copyright 2019 Apereo
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

package com.tle.core.taxonomy;

import java.util.List;
import java.util.Map;

import com.tle.common.beans.exception.InvalidDataException;
import com.tle.common.Pair;
import com.tle.common.beans.exception.IllegalOperationException;
import com.tle.common.taxonomy.RemoteTaxonomyService;
import com.tle.common.taxonomy.SelectionRestriction;
import com.tle.common.taxonomy.Taxonomy;
import com.tle.core.entity.EntityEditingBean;
import com.tle.core.entity.service.AbstractEntityService;

public interface TaxonomyService extends AbstractEntityService<EntityEditingBean, Taxonomy>, RemoteTaxonomyService
{
	/**
	 * Get term by full term path
	 * 
	 * @param taxonomyUuid
	 * @param fullTermPath
	 * @return
	 */
	TermResult getTerm(String taxonomyUuid, String fullTermPath);

	/**
	 * @param parentFullTermPath use null or empty string to retrieve root
	 *            terms.
	 */
	List<TermResult> getChildTerms(String taxonomyUuid, String parentFullTermPath);

	/**
	 * @return A pair containing the number of available results (if the search
	 *         were to be unlimited) and the list of TermResults
	 */
	Pair<Long, List<TermResult>> searchTerms(String taxonomyUuid, String query, SelectionRestriction restriction,
		int limit, boolean searchFullTerm);

	/**
	 * Retrieve custom data from a taxonomy node.
	 * 
	 * @param taxonomyUuid
	 * @param fullTermPath
	 * @param key
	 * @return
	 */
	String getDataForTerm(String taxonomyUuid, String fullTermPath, String key);

	/**
	 * @param taxonomyUuid
	 * @param parentFullTermPath
	 * @param termValue
	 * @param createHierarchy Create as many terms as required to ensure each
	 *            part of parentFullTermPath exists
	 * @throws IllegalOperationException If the data source does not support
	 *             term addition
	 */
	TermResult addTerm(String taxonomyUuid, String parentFullTermPath, String termValue, boolean createHierarchy);

	/**
	 * @param taxonomyUuid
	 * @param parentFullTermPath
	 * @param termValue
	 * @throws InvalidDataException
	 */
	void validateTerm(String taxonomyUuid, String parentFullTermPath, String termValue, boolean requireParent)
		throws InvalidDataException;

	/**
	 * Is taxonomy readonly
	 * 
	 * @param taxonomyUuid
	 * @return boolean
	 */
	boolean isTaxonomyReadonly(String taxonomyUuid);

	/**
	 * Get TermResult by term uuid
	 * 
	 * @param taxonomy
	 * @param termUuid
	 * @return
	 */
	TermResult getTermResultByUuid(String taxonomyUuid, String termUuid);

	/**
	 * Get a data value for a key against a term
	 * 
	 * @param taxonomy
	 * @param termUuid
	 * @param dataKey
	 * @return String
	 */
	String getDataByTermUuid(String taxonomyUuid, String termUuid, String dataKey);

	/**
	 * Get all data for a term
	 * 
	 * @param taxonomyUuid
	 * @param termUuid
	 * @return
	 */
	Map<String, String> getAllDataByTermUuid(String taxonomyUuid, String termUuid);
}
