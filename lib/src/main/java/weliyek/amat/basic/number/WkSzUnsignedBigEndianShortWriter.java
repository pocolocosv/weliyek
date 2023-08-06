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
package weliyek.amat.basic.number;

import java.util.List;
import java.util.Optional;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.WkSzPacketWriterField;
import weliyek.amat.base.output.WkSzPacketWriterFieldCore;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.base.output.WkSzPacketWriterSubfield;

public class WkSzUnsignedBigEndianShortWriter
    implements WkSzNumberWriter<
                        Integer,
                        OperationSettings,
                        SerializingRuntime<WkSzOutputBytestream>,
                        SerializingResult,
                        WkSzUnsignedBigEndianShort>
{

  final WkSzSimplifiedNumberWriterCore<
                        Integer,
                        WkSzUnsignedBigEndianShortWriter,
                        WkSzUnsignedBigEndianShort> operationCore;

  WkSzUnsignedBigEndianShortWriter(
    int index,
    Integer serializable,
    OperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<
      Integer,?,WkSzUnsignedBigEndianShort,?,?,?> serializingfieldCore,
    WkSzSimplifiedNumberSerializerDefinitionCore<
      Integer,?,WkSzUnsignedBigEndianShortWriter,WkSzUnsignedBigEndianShort> definitionCore) {
    this.operationCore = new WkSzSimplifiedNumberWriterCore<>(
                                  index,
                                  serializable,
                                  settings,
                                  parentBytestream,
                                  serializingfieldCore,
                                  definitionCore,
                                  this);
  }

  @Override
  public WkSzUnsignedBigEndianShort definition() {
    return this.operationCore.definition();
  }

  @Override
  public OperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public SerializingRuntime<WkSzOutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<SerializingResult> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSzPacketWriterField<Integer, WkSzUnsignedBigEndianShort, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSzPacketWriterSubfield<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public Integer serializable() {
    return this.operationCore.serializable();
  }

}
