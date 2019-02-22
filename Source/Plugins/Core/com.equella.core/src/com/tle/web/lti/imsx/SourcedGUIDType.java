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

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.4-2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2013.07.10 at 02:09:22 PM EST
//

package com.tle.web.lti.imsx;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * The SourcedGUID complexType is the container for the extended sourcedId. The refAgentInstamceID
 * permits multiple endpoint differentiation.
 *
 * <p>Java class for SourcedGUID.Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SourcedGUID.Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0}refAgentInstanceID" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0}sourcedId"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "SourcedGUID.Type",
    propOrder = {"refAgentInstanceID", "sourcedId"})
public class SourcedGUIDType {

  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  @XmlSchemaType(name = "normalizedString")
  protected String refAgentInstanceID;

  @XmlElement(required = true)
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String sourcedId;

  /**
   * Gets the value of the refAgentInstanceID property.
   *
   * @return possible object is {@link String }
   */
  public String getRefAgentInstanceID() {
    return refAgentInstanceID;
  }

  /**
   * Sets the value of the refAgentInstanceID property.
   *
   * @param value allowed object is {@link String }
   */
  public void setRefAgentInstanceID(String value) {
    this.refAgentInstanceID = value;
  }

  /**
   * Gets the value of the sourcedId property.
   *
   * @return possible object is {@link String }
   */
  public String getSourcedId() {
    return sourcedId;
  }

  /**
   * Sets the value of the sourcedId property.
   *
   * @param value allowed object is {@link String }
   */
  public void setSourcedId(String value) {
    this.sourcedId = value;
  }
}
