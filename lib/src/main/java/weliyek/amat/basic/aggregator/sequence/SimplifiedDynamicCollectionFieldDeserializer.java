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
package weliyek.amat.basic.aggregator.sequence;

import java.util.Collection;

import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.DeserializingFieldCore;
import weliyek.amat.base.input.DeserializingOperation;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.number.NumberDefinition;
import weliyek.amat.basic.number.NumberDeserializing;
import weliyek.ketza.util.array.DynamicSequenceDeserializingCore;

public final class SimplifiedDynamicCollectionFieldDeserializer<
                        T extends Collection<ET>,
                        XS extends OperationSettings,
                        XO extends DynamicCollectionFieldDeserializer<
                                        T,XS,
                                        DeserializingRuntime<InputBytestream>,
                                        DeserializingResult<T>,
                                        XD,ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                        XD extends DynamicCollectionFieldDefinition<
                                        T,XO,?,?,ET,EXS,?,EXO,?,?,?,?,VXS,?>,
                        ZT extends Number,
                        ZXS extends OperationSettings,
                        ZXO extends NumberDeserializing<ZT,ZXS,?,?,ZXD>,
                        ZXD extends NumberDefinition<ZT,?>,
                        ET,
                        EXS extends OperationSettings,
                        EXD extends DefinitionSegment<ET,?>,
                        EXO extends DeserializingOperation<ET,EXS,?,?,EXD>,
                        VXS extends VariableLengthSettings>
    extends DynamicSequenceDeserializingCore<
                        T, XS,
                        InputBytestream,
                        InputBytestreamGeneralBase<? extends InputBytestream>,
                        DeserializingRuntime<InputBytestream>,
                        ReadingRuntimeControl<
                          InputBytestream,
                          InputBytestreamGeneralBase<? extends InputBytestream>,
                          DeserializingRuntime<InputBytestream>>,
                        DeserializingResult<T>,
                        XO,
                        SimplifiedDynamicCollectionFieldDeserializer<
                          T,XS,XO,XD,ZT,ZXS,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                        XD,
                        InputBytestreamGeneralBase<?>,
                        ZT, ZXS, ZXO, ZXD, VXS,
                        VariableSizeCollectionFieldDeserializer<T,VXS,ET,EXS,EXD,EXO>, // VXO
                        VariableSizeCollectionField<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>, // VXD
                        SimplifiedDynamicCollectionDefinitionCore<T,XS,XO,XD,?,?,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?,?>>
    implements DynamicCollectionFieldDeserializer<
                        T, XS,
                        DeserializingRuntime<InputBytestream>,
                        DeserializingResult<T>,
                        XD, ZT, ZXO, ZXD, ET, EXS, EXD, EXO,
                        VXS>
{

  public SimplifiedDynamicCollectionFieldDeserializer(
    int index,
    XS settings,
    InputBytestreamGeneralBase<?> parentBytestream,
    DeserializingFieldCore<T,?,XD,?,?,?> packetfieldCore,
    SimplifiedDynamicCollectionDefinitionCore<
      T,XS,XO,XD,?,?,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?,?> definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, packetfieldCore, definitionCore, operationBody);
  }

  @Override
  protected T onFullReadingCompletion() {
    return variableSequence().field().get()
                             .firstOperation().get()
                             .result().get()
                             .deserialized().get();
  }

  @Override
  protected void onPartialReadingCompletion() {
    // Nothing to do.
  }

  @Override
  protected
  SimplifiedDynamicCollectionFieldDeserializer<T, XS, XO, XD, ZT, ZXS, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>
  getThis() {
    return this;
  }

}
