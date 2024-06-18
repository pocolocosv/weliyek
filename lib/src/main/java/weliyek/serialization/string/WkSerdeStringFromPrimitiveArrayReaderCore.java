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
package weliyek.serialization.string;

import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.util.array.WkPrimitiveArray;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayDefinition;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayReader;

public abstract class WkSerdeStringFromPrimitiveArrayReaderCore<
                        XS extends WkSerdeDtreeOperationSettings,
                        XB extends WkSerdeDtreeBytestreamInput,
                        XBC extends WkSerdeDtreeBytestreamInputBase<? extends XB>,
                        XQ extends WkSerdeDtreeOperationInputRuntime<XB>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<XB,XBC,XQ>,
                        XR extends WkSerdeDtreeOperationResult<String>,
                        XO extends WkSerdeStringFromPrimitiveArrayReader<XS,XQ,XR,XD,SX,SXD,SXO>,
                        XOC extends WkSerdeStringFromPrimitiveArrayReaderCore<
                                        XS,XB,XBC,XQ,XQC,XR,XO,?,XD,XDC,AXB,SX,SXS,SXO,SXD>,
                        XD extends WkSerdeStringFromPrimitiveArrayDefinition<XO,?,? extends SXD>,
                        XDC extends WkSerdeStringFromPrimitiveArrayDefinitionCore<
                                        XS,XB,XBC,XQC,XR,XO,XOC,XD,?,AXB,
                                        ?,?,?,?,?,?,?,?,?,?,
                                        SX,SXS,SXO,SXD,?,?,?,? extends SXD,? extends XD,?>,
                        AXB extends WkSerdeDtreeBytestreamInputBase<?>,
                        SX extends WkPrimitiveArray<?,?>,
                        SXS extends WkSerdeDtreeOperationSettings,
                        SXO extends WkSerdeDtreePrimitiveArrayReader<SX,SXS,?,?,SXD>,
                        SXD extends WkSerdeDtreePrimitiveArrayDefinition<SX>>
       extends WkSerdeDtreeAggregatorMsgReaderCore<String, XS, XB, XBC, XQ, XQC, XR, XD, XDC, XO, XOC, AXB>
       implements WkSerdeStringFromPrimitiveArrayReader<XS, XQ, XR, XD, SX, SXD, SXO>
{

  protected WkSerdeStringFromPrimitiveArrayReaderCore(
    int index,
    XS settings,
    AXB parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> readerFieldCore,
    XDC definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, readerFieldCore, definitionCore, operationBody);
  }

  @Override
  protected final String onFullReadingCompletion() {
    String aggregatedStr = definitionCore().primitiveArrayDeserializingStringAggregator.apply(body());
    return aggregatedStr;
  }

  protected SX getPrimitiveArray() {
    if ( ! WkSerdeStringFromPrimitiveArrayReader.isPrimitiveArrayReady(body())) {
      throw new IllegalArgumentException();
    }
    return primitiveArray().get().firstOperation().get()
                           .result().get().serializable().get();
  }

  @Override
  protected void onAggregatorReadingInitialization() {
    onStringFromPrimitiveReadingInitialization();
  }

  protected abstract void onStringFromPrimitiveReadingInitialization();

  @Override
  public Optional<WkSerdeDtreeMsgInputField<SX, SXD, SXO>> primitiveArray() {
    return Optional.ofNullable(getSubfieldpacketFor(definitionCore().primitiveArraySubcomponent).asPacket());
  }

}
