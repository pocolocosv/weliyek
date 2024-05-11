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

import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgReader;

public class WkSerdeDynamicByteArrayReader<
                        ZT extends Number,
                        ZXO extends WkSerdeDtreeNumberMsgReader<ZT,WkSerdeDtreeOperationSettings,?,?,ZXD>,
                        ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>>
    implements WkSerdeDtreeByteArrayReader<
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<WkByteArray>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>>,
               WkSerdeDtreeDynamicPrimitiveArrayReader<
                        WkByteArray,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<WkByteArray>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        ZT,
                        ZXO,
                        ZXD,
                        WkSerdeDtreeVariableSizeByteArrayReader,
                        WkSerdeDtreeVariableSizeByteArray>
{

  final WkSerdeDtreeDynamicPrimitiveArrayReaderCore<
                        WkByteArray,
                        WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        ZT, ZXO, ZXD,
                        WkSerdeDtreeVariableSizeByteArrayReader,
                        WkSerdeDtreeVariableSizeByteArray> operationCore;

  WkSerdeDynamicByteArrayReader(
    int index,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> readerFieldCore,
    WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
      WkByteArray, WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
      WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,?,?,ZT,ZXD,ZXO,?,?,? extends ZXD,
      WkSerdeDtreeVariableSizeByteArray,WkSerdeDtreeVariableSizeByteArrayReader,
      ?,?,? extends WkSerdeDtreeVariableSizeByteArray,
      ? extends WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>> definitionCore) {
    this.operationCore = new WkSerdeDtreeDynamicPrimitiveArrayReaderCore<
                                WkByteArray,
                                WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                                WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                ZT, ZXO, ZXD,
                                WkSerdeDtreeVariableSizeByteArrayReader,
                                WkSerdeDtreeVariableSizeByteArray>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    readerFieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public List<WkSerdeDtreeMsgInputField<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD> definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput> dashboard() {
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
  public WkSerdeDtreeMsgInputField<?,?,?> parentField() {
    return this.operationCore.parentField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public Optional<WkSerdeDtreeMsgInputField<ZT, ZXD, ZXO>> size() {
    return this.operationCore.size();
  }

  @Override
  public
  Optional<WkSerdeDtreeMsgInputField<WkByteArray, WkSerdeDtreeVariableSizeByteArray, WkSerdeDtreeVariableSizeByteArrayReader>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public int getRequestedLength() {
    return size().get().firstOperation().get().result().get().serializable().get().intValue();
  }

}
