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
package weliyek.util.array;

import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.sequence.WkSerdeUtilsPrimitiveArray;

public class WkSerdeDtreeFixedSizeByteArrayWriter
    implements WkSerdeDtreeByteArrayWriter<
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<WkByteArray>,
                        WkSerdeDtreeFixedSizeByteArray>,
               WkSerdeDtreeGenericFixedSizePrimitiveArrayWriter<
                        WkByteArray,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<WkByteArray>,
                        WkSerdeDtreeFixedSizeByteArray>
{

  final WkSerdeDtreeGenericPrimitiveArrayWriterCoreSimplified<
                        WkByteArray,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeDtreeFixedSizeByteArrayWriter> operationCore;

  WkSerdeDtreeFixedSizeByteArrayWriter(
    int index,
    WkSerdeDtreeMsgOutputFieldCore<WkByteArray,WkSerdeDtreeOperationSettings,?,?,WkSerdeDtreeBytestreamOutputBase<?>,?,?,?>
      msgFieldCore,
    WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<
      WkByteArray,?,?,WkSerdeDtreeOperationSettings,WkSerdeDtreeFixedSizeByteArrayWriter,WkSerdeDtreeFixedSizeByteArray> definitionCore) {
    this.operationCore = new WkSerdeDtreeGenericPrimitiveArrayWriterCoreSimplified<
                                  WkByteArray,
                                  WkSerdeDtreeOperationSettings,
                                  WkSerdeDtreeFixedSizeByteArray,
                                  WkSerdeDtreeFixedSizeByteArrayWriter>(
                                      index,
                                      msgFieldCore,
                                      definitionCore,
                                      this,
                                      WkSerdeUtilsPrimitiveArray::onFixedSizeSerilizingInitialization);
  }

  @Override
  public WkByteArray serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public List<WkSerdeDtreeMsgOutputField<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public WkSerdeDtreeFixedSizeByteArray definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<WkByteArray>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSerdeDtreeMsgOutputField<?,?,?> parentField() {
    return this.operationCore.parentField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public int getRequestedLength() {
    return this.operationCore.getRequestedLength();
  }

}
