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

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the com.tle.web.ltilis package.
 *
 * <p>An ObjectFactory allows you to programatically construct new instances of the Java
 * representation for XML content. The Java representation of XML content can consist of schema
 * derived interfaces and classes representing the binding of schema type definitions, element
 * declarations and model groups. Factory methods for each of these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

  private static final QName _ImsxCodeMajor_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_codeMajor");
  private static final QName _RefAgentInstanceID_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "refAgentInstanceID");
  private static final QName _ImsxPOXBody_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_POXBody");
  private static final QName _ResultRecord_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "resultRecord");
  private static final QName _ImsxPOXEnvelopeResponse_QNAME =
      new QName(
          "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_POXEnvelopeResponse");
  private static final QName _Language_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "language");
  private static final QName _ImsxPOXHeader_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_POXHeader");
  private static final QName _ImsxMessageRefIdentifier_QNAME =
      new QName(
          "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_messageRefIdentifier");
  private static final QName _ImsxSendingAgentIdentifier_QNAME =
      new QName(
          "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
          "imsx_sendingAgentIdentifier");
  private static final QName _Result_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "result");
  private static final QName _ImsxVersion_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_version");
  private static final QName _ImsxPOXResponseHeaderInfo_QNAME =
      new QName(
          "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
          "imsx_POXResponseHeaderInfo");
  private static final QName _ImsxDescription_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_description");
  private static final QName _ImsxCodeMinorFieldValue_QNAME =
      new QName(
          "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_codeMinorFieldValue");
  private static final QName _ImsxPOXRequestHeaderInfo_QNAME =
      new QName(
          "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_POXRequestHeaderInfo");
  private static final QName _SourcedGUID_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "sourcedGUID");
  private static final QName _ImsxPOXEnvelopeRequest_QNAME =
      new QName(
          "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_POXEnvelopeRequest");
  private static final QName _ResultScore_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "resultScore");
  private static final QName _ImsxCodeMinorField_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_codeMinorField");
  private static final QName _ImsxCodeMinor_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_codeMinor");
  private static final QName _ImsxMessageIdentifier_QNAME =
      new QName(
          "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_messageIdentifier");
  private static final QName _ImsxStatusInfo_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_statusInfo");
  private static final QName _TextString_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "textString");
  private static final QName _ImsxSeverity_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_severity");
  private static final QName _ImsxCodeMinorFieldName_QNAME =
      new QName(
          "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "imsx_codeMinorFieldName");
  private static final QName _ImsxOperationRefIdentifier_QNAME =
      new QName(
          "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
          "imsx_operationRefIdentifier");
  private static final QName _SourcedId_QNAME =
      new QName("http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0", "sourcedId");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: com.tle.web.ltilis
   */
  public ObjectFactory() {}

  /** Create an instance of {@link ResultType } */
  public ResultType createResultType() {
    return new ResultType();
  }

  /** Create an instance of {@link ReplaceResultResponse } */
  public ReplaceResultResponse createReplaceResultResponse() {
    return new ReplaceResultResponse();
  }

  /** Create an instance of {@link ImsxResponseHeaderInfoType } */
  public ImsxResponseHeaderInfoType createImsxResponseHeaderInfoType() {
    return new ImsxResponseHeaderInfoType();
  }

  /** Create an instance of {@link ReplaceResultRequest } */
  public ReplaceResultRequest createReplaceResultRequest() {
    return new ReplaceResultRequest();
  }

  /** Create an instance of {@link ResultRecordType } */
  public ResultRecordType createResultRecordType() {
    return new ResultRecordType();
  }

  /** Create an instance of {@link SourcedGUIDType } */
  public SourcedGUIDType createSourcedGUIDType() {
    return new SourcedGUIDType();
  }

  /** Create an instance of {@link TextType } */
  public TextType createTextType() {
    return new TextType();
  }

  /** Create an instance of {@link ImsxPOXBodyType } */
  public ImsxPOXBodyType createImsxPOXBodyType() {
    return new ImsxPOXBodyType();
  }

  /** Create an instance of {@link ImsxRequestHeaderInfoType } */
  public ImsxRequestHeaderInfoType createImsxRequestHeaderInfoType() {
    return new ImsxRequestHeaderInfoType();
  }

  /** Create an instance of {@link ImsxPOXEnvelopeType } */
  public ImsxPOXEnvelopeType createImsxPOXEnvelopeType() {
    return new ImsxPOXEnvelopeType();
  }

  /** Create an instance of {@link ImsxCodeMinorType } */
  public ImsxCodeMinorType createImsxCodeMinorType() {
    return new ImsxCodeMinorType();
  }

  /** Create an instance of {@link ImsxCodeMinorFieldType } */
  public ImsxCodeMinorFieldType createImsxCodeMinorFieldType() {
    return new ImsxCodeMinorFieldType();
  }

  /** Create an instance of {@link ImsxStatusInfoType } */
  public ImsxStatusInfoType createImsxStatusInfoType() {
    return new ImsxStatusInfoType();
  }

  /** Create an instance of {@link DeleteResultResponse } */
  public DeleteResultResponse createDeleteResultResponse() {
    return new DeleteResultResponse();
  }

  /** Create an instance of {@link DeleteResultRequest } */
  public DeleteResultRequest createDeleteResultRequest() {
    return new DeleteResultRequest();
  }

  /** Create an instance of {@link ReadResultRequest } */
  public ReadResultRequest createReadResultRequest() {
    return new ReadResultRequest();
  }

  /** Create an instance of {@link ReadResultResponse } */
  public ReadResultResponse createReadResultResponse() {
    return new ReadResultResponse();
  }

  /** Create an instance of {@link ImsxPOXHeaderType } */
  public ImsxPOXHeaderType createImsxPOXHeaderType() {
    return new ImsxPOXHeaderType();
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link ImsxCodeMajorType }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_codeMajor")
  public JAXBElement<ImsxCodeMajorType> createImsxCodeMajor(ImsxCodeMajorType value) {
    return new JAXBElement<ImsxCodeMajorType>(
        _ImsxCodeMajor_QNAME, ImsxCodeMajorType.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "refAgentInstanceID")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  public JAXBElement<String> createRefAgentInstanceID(String value) {
    return new JAXBElement<String>(_RefAgentInstanceID_QNAME, String.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link ImsxPOXBodyType }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_POXBody")
  public JAXBElement<ImsxPOXBodyType> createImsxPOXBody(ImsxPOXBodyType value) {
    return new JAXBElement<ImsxPOXBodyType>(_ImsxPOXBody_QNAME, ImsxPOXBodyType.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link ResultRecordType }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "resultRecord")
  public JAXBElement<ResultRecordType> createResultRecord(ResultRecordType value) {
    return new JAXBElement<ResultRecordType>(
        _ResultRecord_QNAME, ResultRecordType.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link ImsxPOXEnvelopeType }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_POXEnvelopeResponse")
  public JAXBElement<ImsxPOXEnvelopeType> createImsxPOXEnvelopeResponse(ImsxPOXEnvelopeType value) {
    return new JAXBElement<ImsxPOXEnvelopeType>(
        _ImsxPOXEnvelopeResponse_QNAME, ImsxPOXEnvelopeType.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "language")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  public JAXBElement<String> createLanguage(String value) {
    return new JAXBElement<String>(_Language_QNAME, String.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link ImsxPOXHeaderType }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_POXHeader")
  public JAXBElement<ImsxPOXHeaderType> createImsxPOXHeader(ImsxPOXHeaderType value) {
    return new JAXBElement<ImsxPOXHeaderType>(
        _ImsxPOXHeader_QNAME, ImsxPOXHeaderType.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_messageRefIdentifier")
  public JAXBElement<String> createImsxMessageRefIdentifier(String value) {
    return new JAXBElement<String>(_ImsxMessageRefIdentifier_QNAME, String.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_sendingAgentIdentifier")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  public JAXBElement<String> createImsxSendingAgentIdentifier(String value) {
    return new JAXBElement<String>(_ImsxSendingAgentIdentifier_QNAME, String.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link ResultType }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "result")
  public JAXBElement<ResultType> createResult(ResultType value) {
    return new JAXBElement<ResultType>(_Result_QNAME, ResultType.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ImsxGWSVersionValueType }{@code >}}
   */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_version")
  public JAXBElement<ImsxGWSVersionValueType> createImsxVersion(ImsxGWSVersionValueType value) {
    return new JAXBElement<ImsxGWSVersionValueType>(
        _ImsxVersion_QNAME, ImsxGWSVersionValueType.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ImsxResponseHeaderInfoType }{@code
   * >}}
   */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_POXResponseHeaderInfo")
  public JAXBElement<ImsxResponseHeaderInfoType> createImsxPOXResponseHeaderInfo(
      ImsxResponseHeaderInfoType value) {
    return new JAXBElement<ImsxResponseHeaderInfoType>(
        _ImsxPOXResponseHeaderInfo_QNAME, ImsxResponseHeaderInfoType.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_description")
  public JAXBElement<String> createImsxDescription(String value) {
    return new JAXBElement<String>(_ImsxDescription_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ImsxCodeMinorValueType }{@code >}}
   */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_codeMinorFieldValue")
  public JAXBElement<ImsxCodeMinorValueType> createImsxCodeMinorFieldValue(
      ImsxCodeMinorValueType value) {
    return new JAXBElement<ImsxCodeMinorValueType>(
        _ImsxCodeMinorFieldValue_QNAME, ImsxCodeMinorValueType.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ImsxRequestHeaderInfoType }{@code >}}
   */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_POXRequestHeaderInfo")
  public JAXBElement<ImsxRequestHeaderInfoType> createImsxPOXRequestHeaderInfo(
      ImsxRequestHeaderInfoType value) {
    return new JAXBElement<ImsxRequestHeaderInfoType>(
        _ImsxPOXRequestHeaderInfo_QNAME, ImsxRequestHeaderInfoType.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link SourcedGUIDType }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "sourcedGUID")
  public JAXBElement<SourcedGUIDType> createSourcedGUID(SourcedGUIDType value) {
    return new JAXBElement<SourcedGUIDType>(_SourcedGUID_QNAME, SourcedGUIDType.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link ImsxPOXEnvelopeType }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_POXEnvelopeRequest")
  public JAXBElement<ImsxPOXEnvelopeType> createImsxPOXEnvelopeRequest(ImsxPOXEnvelopeType value) {
    return new JAXBElement<ImsxPOXEnvelopeType>(
        _ImsxPOXEnvelopeRequest_QNAME, ImsxPOXEnvelopeType.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link TextType }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "resultScore")
  public JAXBElement<TextType> createResultScore(TextType value) {
    return new JAXBElement<TextType>(_ResultScore_QNAME, TextType.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ImsxCodeMinorFieldType }{@code >}}
   */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_codeMinorField")
  public JAXBElement<ImsxCodeMinorFieldType> createImsxCodeMinorField(
      ImsxCodeMinorFieldType value) {
    return new JAXBElement<ImsxCodeMinorFieldType>(
        _ImsxCodeMinorField_QNAME, ImsxCodeMinorFieldType.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link ImsxCodeMinorType }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_codeMinor")
  public JAXBElement<ImsxCodeMinorType> createImsxCodeMinor(ImsxCodeMinorType value) {
    return new JAXBElement<ImsxCodeMinorType>(
        _ImsxCodeMinor_QNAME, ImsxCodeMinorType.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_messageIdentifier")
  public JAXBElement<String> createImsxMessageIdentifier(String value) {
    return new JAXBElement<String>(_ImsxMessageIdentifier_QNAME, String.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link ImsxStatusInfoType }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_statusInfo")
  public JAXBElement<ImsxStatusInfoType> createImsxStatusInfo(ImsxStatusInfoType value) {
    return new JAXBElement<ImsxStatusInfoType>(
        _ImsxStatusInfo_QNAME, ImsxStatusInfoType.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "textString")
  public JAXBElement<String> createTextString(String value) {
    return new JAXBElement<String>(_TextString_QNAME, String.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link ImsxSeverityType }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_severity")
  public JAXBElement<ImsxSeverityType> createImsxSeverity(ImsxSeverityType value) {
    return new JAXBElement<ImsxSeverityType>(
        _ImsxSeverity_QNAME, ImsxSeverityType.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_codeMinorFieldName",
      defaultValue = "TargetEndSystem")
  public JAXBElement<String> createImsxCodeMinorFieldName(String value) {
    return new JAXBElement<String>(_ImsxCodeMinorFieldName_QNAME, String.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "imsx_operationRefIdentifier")
  public JAXBElement<String> createImsxOperationRefIdentifier(String value) {
    return new JAXBElement<String>(_ImsxOperationRefIdentifier_QNAME, String.class, null, value);
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}} */
  @XmlElementDecl(
      namespace = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0",
      name = "sourcedId")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  public JAXBElement<String> createSourcedId(String value) {
    return new JAXBElement<String>(_SourcedId_QNAME, String.class, null, value);
  }
}
