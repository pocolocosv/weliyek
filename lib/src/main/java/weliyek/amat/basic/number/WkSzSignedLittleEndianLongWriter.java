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

public final class WkSzSignedLittleEndianLongWriter
        implements WkSzNumberWriter<
                        Long,
                        OperationSettings,
                        SerializingRuntime<WkSzOutputBytestream>,
                        SerializingResult,
                        WkSzSignedLittleEndianLong>
{

  final WkSzSimplifiedNumberWriterCore<
                        Long,
                        WkSzSignedLittleEndianLongWriter,
                        WkSzSignedLittleEndianLong> operationCore;

  WkSzSignedLittleEndianLongWriter(
    int index,
    Long serializable,
    OperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<
      Long,?,WkSzSignedLittleEndianLong,?,?,?> serializingfieldCore,
    WkSzSimplifiedNumberSerializerDefinitionCore<
      Long,?,WkSzSignedLittleEndianLongWriter,WkSzSignedLittleEndianLong> definitionCore) {
    operationCore = new WkSzSimplifiedNumberWriterCore<>(
                            index,
                            serializable,
                            settings,
                            parentBytestream,
                            serializingfieldCore,
                            definitionCore,
                            this);
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
  public Long serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public WkSzPacketWriterField<Long, WkSzSignedLittleEndianLong, ?> packetField() {
    return this.operationCore.packet();
  }

  @Override
  public WkSzSignedLittleEndianLong definition() {
    return this.operationCore.definition();
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
  public String toString() {
    return this.operationCore.toString();
  }

}
