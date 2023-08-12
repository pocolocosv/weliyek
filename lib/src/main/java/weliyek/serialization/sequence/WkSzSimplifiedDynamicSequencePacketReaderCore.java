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
package weliyek.serialization.sequence;

import java.util.Collection;

import weliyek.serialization.WkSzDefinition;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderFieldCore;
import weliyek.serialization.WkSzPacketReaderOperation;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzReadingRuntimeControl;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberReader;
import weliyek.util.array.WkSzDynamicSequencePacketReaderOperationCore;

public final class WkSzSimplifiedDynamicSequencePacketReaderCore<
                        T extends Collection<ET>,
                        XS extends WkSzOperationSettings,
                        XO extends WkSzDynamicCollectionPacketReaderOperation<
                                        T,XS,
                                        WkSzReadingRuntime<WkSzInputBytestream>,
                                        WkSzReadingResult<T>,
                                        XD,ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                        XD extends WkSzDynamicCollectionDefinition<
                                        T,XO,?,?,ET,EXS,?,EXO,?,?,?,?,VXS,?>,
                        ZT extends Number,
                        ZXS extends WkSzOperationSettings,
                        ZXO extends WkSzNumberReader<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSzNumberDefinition<ZT,?>,
                        ET,
                        EXS extends WkSzOperationSettings,
                        EXD extends WkSzDefinition<ET,?>,
                        EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>,
                        VXS extends WkSzVariableLengthOperationSettings>
    extends WkSzDynamicSequencePacketReaderOperationCore<
                        T, XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSzReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<T>,
                        XO,
                        WkSzSimplifiedDynamicSequencePacketReaderCore<
                          T,XS,XO,XD,ZT,ZXS,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                        XD,
                        WkSzInputBytestreamBase<?>,
                        ZT, ZXS, ZXO, ZXD, VXS,
                        VariableSizeCollectionFieldDeserializer<T,VXS,ET,EXS,EXD,EXO>, // VXO
                        VariableSizeCollectionField<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>, // VXD
                        WkSzSimplifiedDynamicCollectionStructDefinition<T,XS,XO,XD,?,?,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?,?>>
    implements WkSzDynamicCollectionPacketReaderOperation<
                        T, XS,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<T>,
                        XD, ZT, ZXO, ZXD, ET, EXS, EXD, EXO,
                        VXS>
{

  public WkSzSimplifiedDynamicSequencePacketReaderCore(
    int index,
    XS settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<T,?,XD,?,?,?> packetfieldCore,
    WkSzSimplifiedDynamicCollectionStructDefinition<
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
  WkSzSimplifiedDynamicSequencePacketReaderCore<T, XS, XO, XD, ZT, ZXS, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>
  getThis() {
    return this;
  }

}
