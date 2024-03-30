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
package weliyek.serialization.number;

import weliyek.serialization.WkBasicResultSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSimplifiedDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSimplifiedEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.WkSzReadEngineFactory;
import weliyek.serialization.WkSzWriteEngineFactory;

public final class WkSerdeDtreeNumberDefinitionCoreSimplified<
                        T extends Number,
                        XO extends WkSerdeDtreeNumberReader<
                                      T,
                                      WkSettingsSrlzPacketOperationData,
                                      WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                                      WkResultSrlzPacketOperationData<T>,
                                      D>,
                        YO extends WkSerdeDtreeNumberWriter<
                                      T,
                                      WkSettingsSrlzPacketOperationData,
                                      WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                      WkResultSrlzPacketOperationData<T>,
                                      D>,
                        D extends WkSerdeDtreeNumberDefinition<T>>
    extends WkSerdeDtreeNumberDefinitionCore<
                        T,
                        WkSettingsSrlzPacketOperationData,
                        WkDecodingRuntimeSrlzPacketOperationCtrl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>>,
                          WkResultSrlzPacketOperationData<T>,
                        D, XO,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,
                        WkEncodingRuntimeSrlzPacketOperationCtrl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>,
                        WkResultSrlzPacketOperationData<T>,
                        D, YO,
                        WkSzOutputBytestreamBase<?>,
                        D,
                        WkSerdeDtreeNumberDefinitionCoreSimplified<T,XO,YO,D>>
{

  public WkSerdeDtreeNumberDefinitionCoreSimplified(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    WkSzPacketReaderOperationCoreFactory<
      T, WkSettingsSrlzPacketOperationData, D, WkSerdeDtreeNumberDefinitionCoreSimplified<T,XO,YO,D>,
      XO, WkSzInputBytestreamBase<?>> rxOpFactory,
    WkSzReadEngineFactory<
      T, ? super WkDecodingRuntimeSrlzPacketOperationCtrl<WkSzInputBytestream, WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
      WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>>, ? super XO> rxSerializerFactory,
    WkSzPacketWriterOperationCoreFactory<
      T, WkSettingsSrlzPacketOperationData, D, WkSerdeDtreeNumberDefinitionCoreSimplified<T,XO,YO,D>,
      YO, WkSzOutputBytestreamBase<?>> txOpFactory,
    WkSzWriteEngineFactory<
      T, ? super WkEncodingRuntimeSrlzPacketOperationCtrl<WkSzOutputBytestream, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
      WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>, ? super YO> txSerializerFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          componentCore,
          WkSimplifiedDecodingRuntimeSrlzPacketOperationCtrl::new,
          WkBasicResultSrlzPacketOperationData::new,
          rxOpFactory,
          rxSerializerFactory,
          WkSimplifiedEncodingRuntimeSrlzPacketOperationCtrl::new,
          WkBasicResultSrlzPacketOperationData::new,
          txOpFactory,
          txSerializerFactory,
          definitionBody,
          serializableClass);
  }

  @Override
  protected WkSerdeDtreeNumberDefinitionCoreSimplified<T,XO,YO,D> getThis() {
    return this;
  }

}
