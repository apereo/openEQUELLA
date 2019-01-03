/*
 * Licensed to the Apereo Foundation under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
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
 * 
 *                 The RequestHeaderInfo complexType is the container for the information to be placed in the SOAP/POX header for the request message transmitted for the associated operation call.
 *             
 * 
 * <p>Java class for imsx_RequestHeaderInfo.Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="imsx_RequestHeaderInfo.Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0}imsx_version"/>
 *         &lt;element ref="{http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0}imsx_messageIdentifier"/>
 *         &lt;element ref="{http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0}imsx_sendingAgentIdentifier" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "imsx_RequestHeaderInfo.Type", propOrder = {
    "imsxVersion",
    "imsxMessageIdentifier",
    "imsxSendingAgentIdentifier"
})
public class ImsxRequestHeaderInfoType {

    @XmlElement(name = "imsx_version", required = true)
    protected ImsxGWSVersionValueType imsxVersion;
    @XmlElement(name = "imsx_messageIdentifier", required = true)
    protected String imsxMessageIdentifier;
    @XmlElement(name = "imsx_sendingAgentIdentifier")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String imsxSendingAgentIdentifier;

    /**
     * Gets the value of the imsxVersion property.
     * 
     * @return
     *     possible object is
     *     {@link ImsxGWSVersionValueType }
     *     
     */
    public ImsxGWSVersionValueType getImsxVersion() {
        return imsxVersion;
    }

    /**
     * Sets the value of the imsxVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImsxGWSVersionValueType }
     *     
     */
    public void setImsxVersion(ImsxGWSVersionValueType value) {
        this.imsxVersion = value;
    }

    /**
     * Gets the value of the imsxMessageIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImsxMessageIdentifier() {
        return imsxMessageIdentifier;
    }

    /**
     * Sets the value of the imsxMessageIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImsxMessageIdentifier(String value) {
        this.imsxMessageIdentifier = value;
    }

    /**
     * Gets the value of the imsxSendingAgentIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImsxSendingAgentIdentifier() {
        return imsxSendingAgentIdentifier;
    }

    /**
     * Sets the value of the imsxSendingAgentIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImsxSendingAgentIdentifier(String value) {
        this.imsxSendingAgentIdentifier = value;
    }

}
