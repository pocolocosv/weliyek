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
package weliyek.amat.basic.string;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.DeserializingField;
import weliyek.amat.base.input.DeserializingFieldCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.DeserializingSubfieldHandler;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.basic.number.NumberDefinition;
import weliyek.amat.basic.number.NumberDeserializing;
import weliyek.ketza.util.array.ByteArrayWrapper;
import weliyek.ketza.util.array.DynamicByteArray;
import weliyek.ketza.util.array.DynamicByteArrayDeserializing;

public class StringWithDynamicSizeBytesDeserializing<
                        ZT extends Number,
                        ZXD extends NumberDefinition<ZT,ZXO>,
                        ZXO extends NumberDeserializing<ZT,OperationSettings,?,?,ZXD>>
    implements StringFromBytesReading<
                        OperationSettings,
                        DeserializingRuntime<InputBytestream>,
                        DeserializingResult<String>,
                        StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>>
{

  final SimplifiedStringFromBytesReadingCore<
                        OperationSettings,
                        StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>,
                        StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        OperationSettings,
                        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>>
                            operationCore;

  StringWithDynamicSizeBytesDeserializing(
    int index,
    OperationSettings settings,
    InputBytestreamGeneralBase<?> parentBytestream,
    DeserializingFieldCore<String,?,StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,?,?,?>
      deserializingfieldCore,
    SimplifiedStringFromBytesCore<
      OperationSettings,StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>,
      StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,?,?,?,
      OperationSettings,DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
      DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,?,?,?,?,?> definitionCore) {
    this.operationCore = new SimplifiedStringFromBytesReadingCore<
        OperationSettings,
        StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>,
        StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
        OperationSettings,
        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>>(
                                      index,
                                      settings,
                                      parentBytestream,
                                      deserializingfieldCore,
                                      definitionCore,
                                      this);
  }

  @Override
  public
  DeserializingSubfieldHandler<
                        ByteArrayWrapper,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        DynamicByteArrayDeserializing<ZT, ZXO, ZXD>>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD> definition() {
    return this.operationCore.definition();
  }

  @Override
  public OperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public DeserializingRuntime<InputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<DeserializingResult<String>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public
  DeserializingField<String, StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<DeserializingSubfieldHandler<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public
  DeserializingSubfieldHandler<ByteArrayWrapper, DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>, DynamicByteArrayDeserializing<ZT, ZXO, ZXD>>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
