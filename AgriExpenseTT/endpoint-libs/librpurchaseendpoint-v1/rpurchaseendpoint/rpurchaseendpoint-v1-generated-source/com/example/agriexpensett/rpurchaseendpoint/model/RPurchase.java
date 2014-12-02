/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-11-17 18:43:33 UTC)
 * on 2014-12-01 at 17:52:06 UTC 
 * Modify at your own risk.
 */

package com.example.agriexpensett.rpurchaseendpoint.model;

/**
 * Model definition for RPurchase.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the rpurchaseendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class RPurchase extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String account;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double cost;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String elementName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Key key;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String keyrep;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer pId;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double qty;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double qtyRemaining;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String quantifier;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer resourceId;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String type;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getAccount() {
    return account;
  }

  /**
   * @param account account or {@code null} for none
   */
  public RPurchase setAccount(java.lang.String account) {
    this.account = account;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getCost() {
    return cost;
  }

  /**
   * @param cost cost or {@code null} for none
   */
  public RPurchase setCost(java.lang.Double cost) {
    this.cost = cost;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getElementName() {
    return elementName;
  }

  /**
   * @param elementName elementName or {@code null} for none
   */
  public RPurchase setElementName(java.lang.String elementName) {
    this.elementName = elementName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Key getKey() {
    return key;
  }

  /**
   * @param key key or {@code null} for none
   */
  public RPurchase setKey(Key key) {
    this.key = key;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getKeyrep() {
    return keyrep;
  }

  /**
   * @param keyrep keyrep or {@code null} for none
   */
  public RPurchase setKeyrep(java.lang.String keyrep) {
    this.keyrep = keyrep;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getPId() {
    return pId;
  }

  /**
   * @param pId pId or {@code null} for none
   */
  public RPurchase setPId(java.lang.Integer pId) {
    this.pId = pId;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getQty() {
    return qty;
  }

  /**
   * @param qty qty or {@code null} for none
   */
  public RPurchase setQty(java.lang.Double qty) {
    this.qty = qty;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getQtyRemaining() {
    return qtyRemaining;
  }

  /**
   * @param qtyRemaining qtyRemaining or {@code null} for none
   */
  public RPurchase setQtyRemaining(java.lang.Double qtyRemaining) {
    this.qtyRemaining = qtyRemaining;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getQuantifier() {
    return quantifier;
  }

  /**
   * @param quantifier quantifier or {@code null} for none
   */
  public RPurchase setQuantifier(java.lang.String quantifier) {
    this.quantifier = quantifier;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getResourceId() {
    return resourceId;
  }

  /**
   * @param resourceId resourceId or {@code null} for none
   */
  public RPurchase setResourceId(java.lang.Integer resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getType() {
    return type;
  }

  /**
   * @param type type or {@code null} for none
   */
  public RPurchase setType(java.lang.String type) {
    this.type = type;
    return this;
  }

  @Override
  public RPurchase set(String fieldName, Object value) {
    return (RPurchase) super.set(fieldName, value);
  }

  @Override
  public RPurchase clone() {
    return (RPurchase) super.clone();
  }

}
