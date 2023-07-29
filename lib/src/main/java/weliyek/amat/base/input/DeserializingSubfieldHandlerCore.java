/*
 * Weliyek Java Library
 * Copyright (C) 2023  Ricardo Villalobos - All Rights Reserved
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package weliyek.amat.base.input;

import java.util.List;
import java.util.function.Predicate;

import weliyek.amat.base.ComponentSegment;
import weliyek.amat.base.ComponentSegmentCore;
import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.SubcomponentHandler;
import weliyek.amat.base.SubfieldHandlerCore;
import weliyek.amat.basic.aggregator.AggregatorDefinition;
import weliyek.amat.basic.aggregator.AggregatorReading;
import weliyek.amat.basic.aggregator.AggregatorReadingCore;
import weliyek.amat.basic.aggregator.SubcomponentHandlerCore;

public class DeserializingSubfieldHandlerCore<
                        ST,
                        SXS extends OperationSettings,
                        SXD extends DefinitionSegment<ST,?>,
                        SXO extends DeserializingOperation<ST,SXS,?,?,SXD>,
                        T,
                        XBC extends InputBytestreamGeneralBase<?>,
                        XD extends AggregatorDefinition<T,?>,
                        XO extends AggregatorReading<T,?,? extends DeserializingRuntime<?>,?,XD>>
    extends SubfieldHandlerCore<
                        SXS, SXD,
                        SubcomponentHandlerCore<ST,SXS,SXD,SXO,?,XBC,XD,XO,?,?,?,?,?,?,? extends SXD,? extends XD>,
                        DeserializingField<ST,SXD,SXO>,
                        DeserializingFieldCore<ST,SXS,SXD,SXO,XBC,XO>,
                        DeserializingSubfieldHandler<ST,SXD,SXO>,
                        AggregatorReadingCore<?,?,?,XBC,?,?,?,XD,XO,?,?,?>,
                        XD>
    implements DeserializingSubfieldHandler<ST,SXD,SXO>
{

  private boolean isRequiredByProto = false;

  public DeserializingSubfieldHandlerCore() {
    super();
  }

  @Override
  protected void onInitialization() {
    final AggregatorReadingCore<?,?,?,?,?,?,?,XD,XO,?,?,?> parentOpCore = parentOperationCore();
    AggregatorDefinition<?,?> parentDef = parentOpCore.definition();
    List<SubcomponentHandler<?,?,?>> requiredSubfields = parentDef.requiredSubfields();
    if(-1 != requiredSubfields.indexOf(subcomponentHandlerCore().body())) {
      this.isRequiredByProto = true;
    } else {
      this.isRequiredByProto = false;
    }
  }

  @Override
  protected void onReset() {
    this.isRequiredByProto = false;
  }

  @Override
  protected DeserializingSubfieldCore<ST,SXS,SXD,SXO,XBC,XO> newPacket() {
    return new DeserializingSubfieldCore<>(1, this);
  }

  @Override
  public boolean deserializedRequiredByProtocol() {
    return this.isRequiredByProto;
  }

  @Override
  public DeserializingSubfieldHandler<ST, SXD, SXO> asSubfield() {
    return this;
  }

  @Override
  protected boolean isFieldOptional() {
    return subcomponentHandlerCore().isRxOptional();
  }

  @Override
  protected boolean testIfOptionalFieldIsToBeEnabled() {
    XO parentOp = parentOperationCore().body();
    Predicate<? super XO> tester = subcomponentHandlerCore().rxOptionalityTest().get();
    return tester.test(parentOp);
  }

  @Override
  public ComponentSegmentCore<ST,SXS,SXD,SXO,XBC,?,?,?,?,? extends SXD> protocolFieldCore() {
    return subcomponentHandlerCore().protocolFieldCore();
  }

  @Override
  protected SXD definition() {
    return protocolField().definition();
  }

  @Override
  protected final ComponentSegment<? extends SXD> protocolField() {
    return protocolFieldCore().asProtocolField();
  }

}
