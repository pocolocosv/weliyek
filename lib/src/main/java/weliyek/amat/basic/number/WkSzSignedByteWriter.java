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

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.output.WkSzPacketWriterField;
import weliyek.amat.base.output.WkSzPacketWriterFieldCore;
import weliyek.amat.base.output.WkSzWritingResult;
import weliyek.amat.base.output.WkSzWritingRuntime;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.base.output.WkSzPacketWriterSubfield;

public final class WkSzSignedByteWriter
    implements WkSzNumberWriter<
                    Byte,
                    WkSzOperationSettings,
                    WkSzWritingRuntime<WkSzOutputBytestream>,
                    WkSzWritingResult,
                    WkSzSignedByte>
{

  static WkSzSimplifiedNumberWriterCore<
                    Byte,
                    WkSzSignedByteWriter,
                    WkSzSignedByte> newCore(
    int index,
    Byte serializable,
    WkSzOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<Byte,?,WkSzSignedByte,?,?,?> writingfieldCore,
    WkSzSimplifiedNumberSerializerDefinitionCore<
      Byte,?,WkSzSignedByteWriter,WkSzSignedByte> defintionCore) {
    return new WkSzSignedByteWriter(
                    index,
                    serializable,
                    settings,
                    parentBytestream,
                    writingfieldCore,
                    defintionCore).writingCore;
  }

  final WkSzSimplifiedNumberWriterCore<
                    Byte,
                    WkSzSignedByteWriter,
                    WkSzSignedByte> writingCore;

  WkSzSignedByteWriter(
    int index,
    Byte serializable,
    WkSzOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<Byte,?,WkSzSignedByte,?,?,?> writingfieldCore,
    WkSzSimplifiedNumberSerializerDefinitionCore<
      Byte,?,WkSzSignedByteWriter,WkSzSignedByte> defintionCore) {
    this.writingCore = new WkSzSimplifiedNumberWriterCore<
                              Byte, WkSzSignedByteWriter, WkSzSignedByte>(
                                    index,
                                    serializable,
                                    settings,
                                    parentBytestream,
                                    writingfieldCore,
                                    defintionCore,
                                    this);
  }

  @Override
  public WkSzSignedByte definition() {
    return this.writingCore.definition();
  }

  @Override
  public WkSzOperationSettings settings() {
    return this.writingCore.settings();
  }

  @Override
  public WkSzWritingRuntime<WkSzOutputBytestream> dashboard() {
    return this.writingCore.dashboard();
  }

  @Override
  public Optional<WkSzWritingResult> result() {
    return this.writingCore.result();
  }

  @Override
  public int index() {
    return this.writingCore.index();
  }

  @Override
  public WkSzPacketWriterField<Byte, WkSzSignedByte, ?> packetField() {
    return this.writingCore.packetField();
  }

  @Override
  public List<WkSzPacketWriterSubfield<?,?,?>> subfields() {
    return this.writingCore.subfields();
  }

  @Override
  public String name() {
    return this.writingCore.name();
  }

  @Override
  public Byte serializable() {
    return this.writingCore.serializable();
  }

  @Override
  public String toString() {
    return this.writingCore.toString();
  }

}
