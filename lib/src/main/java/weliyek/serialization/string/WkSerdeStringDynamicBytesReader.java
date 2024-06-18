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

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgReader;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDynamicByteArray;
import weliyek.util.array.WkSerdeDynamicByteArrayReader;

public class WkSerdeStringDynamicBytesReader<
                        ZT extends Number,
                        ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ZXO extends WkSerdeDtreeNumberMsgReader<ZT,WkSerdeDtreeOperationSettings,?,?,ZXD>>
    implements WkSerdeStringFromBytesReader<
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<String>,
                        WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>>
{

  final WkSerdeStringFromBytesReaderCoreSimplified<
                        WkSerdeDtreeOperationSettings,
                        WkSerdeStringDynamicBytesReader<ZT,ZXD,ZXO>,
                        WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>>
                            operationCore;

  WkSerdeStringDynamicBytesReader(
    int index,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> readerFieldCore,
    WkSerdeStringFromBytesDefinitionCoreSimplified<
      WkSerdeDtreeOperationSettings, WkSerdeStringDynamicBytesReader<ZT, ZXD, ZXO>,
      WkSerdeStringDynamicBytes<ZT, ZXD, ZXO, ?, ?, ? extends ZXD>, ?, ?, ?,
      WkSerdeDtreeOperationSettings, WkSerdeDynamicByteArrayReader<ZT, ZXO, ZXD>,
      WkSerdeDynamicByteArray<ZT, ZXD, ZXO, ?, ?, ? extends ZXD>, ?, ?, ?,
      ? extends WkSerdeDynamicByteArray<ZT, ZXD, ZXO, ?, ?, ? extends ZXD>,
      ? extends WkSerdeStringDynamicBytes<ZT, ZXD, ZXO, ?, ?, ? extends ZXD>> definitionCore) {
    this.operationCore = new WkSerdeStringFromBytesReaderCoreSimplified<
        WkSerdeDtreeOperationSettings,
        WkSerdeStringDynamicBytesReader<ZT,ZXD,ZXO>,
        WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
        WkSerdeDtreeOperationSettings,
        WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>>(
                                      index,
                                      settings,
                                      parentBytestream,
                                      readerFieldCore,
                                      definitionCore,
                                      this);
  }

  @Override
  public
  Optional<WkSerdeDtreeMsgInputField<WkByteArray, WkSerdeDynamicByteArray<ZT, ZXD, ZXO, ?, ?, ? extends ZXD>, WkSerdeDynamicByteArrayReader<ZT, ZXO, ZXD>>>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,?,?,? extends ZXD> definition() {
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
  public Optional<WkSerdeDtreeOperationResult<String>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public
  WkSerdeDtreeMsgInputField<?,?,?> parentField() {
    return this.operationCore.parentField();
  }

  @Override
  public List<WkSerdeDtreeMsgInputField<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public
  Optional<WkSerdeDtreeMsgInputField<WkByteArray, WkSerdeDynamicByteArray<ZT, ZXD, ZXO, ?, ?, ? extends ZXD>, WkSerdeDynamicByteArrayReader<ZT, ZXO, ZXD>>>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
