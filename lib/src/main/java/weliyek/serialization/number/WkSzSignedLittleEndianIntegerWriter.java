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

import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketWriterField;
import weliyek.serialization.WkSzPacketWriterFieldCore;
import weliyek.serialization.WkSzPacketWriterSubfield;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;

public final class WkSzSignedLittleEndianIntegerWriter
        implements WkSzNumberWriter<
                        Integer,
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        WkSzSignedLittleEndianInteger>
{

  final WkSzSimplifiedNumberWriterCore<
                      Integer,
                      WkSzSignedLittleEndianIntegerWriter,
                      WkSzSignedLittleEndianInteger> operationCore;

  WkSzSignedLittleEndianIntegerWriter(
    int index,
    Integer serializable,
    WkSzOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<
      Integer,?,WkSzSignedLittleEndianInteger,?,?,?> serializationfieldCore,
    WkSzSimplifiedNumberSerializerDefinitionCore<
      Integer,?,WkSzSignedLittleEndianIntegerWriter,WkSzSignedLittleEndianInteger> definitionCore) {
    operationCore = new WkSzSimplifiedNumberWriterCore<>(
                                  index,
                                  serializable,
                                  settings,
                                  parentBytestream,
                                  serializationfieldCore,
                                  definitionCore,
                                  this);
  }

  @Override
  public WkSzOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSzWritingRuntime<WkSzOutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSzWritingResult> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public Integer serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public WkSzPacketWriterField<Integer, WkSzSignedLittleEndianInteger, ?> packetField() {
    return this.operationCore.packet();
  }

  @Override
  public List<WkSzPacketWriterSubfield<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public WkSzSignedLittleEndianInteger definition() {
    return this.operationCore.definition();
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
