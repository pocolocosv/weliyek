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

import java.io.IOException;

import weliyek.serialization.WkSzPrimitiveReadEngineFactory;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationCtrl;

public final class WkSignedBigEndianShortSrlzEngineDecoder
    extends WkIntSrlzEngineDecoder<Short>
{

  public static WkSzPrimitiveReadEngineFactory<Short> FACTORY =
      new WkSzPrimitiveReadEngineFactory<>(
              "SINT16BE",
              WkSignedBigEndianShortSrlzEngineDecoder::new);

  private WkSignedBigEndianShortSrlzEngineDecoder(
    WkDecodingRuntimeSrlzPacketOperationCtrl<?,?,?> runtimeCtrl,
    WkNumberSrlzInputPacketDecoderFrameLeafNode<Short,?,?,?,?> readingOperation) {
    super((i) -> Short.valueOf((short)i), runtimeCtrl, readingOperation);
  }

  @Override
  protected int readPrimitiveAsInt() throws IOException {
    return readBigEndianShort();
  }

  @Override
  protected long requestedBytesToIO() {
    return primitiveByteLength();
  }

  @Override
  public final int primitiveByteLength() {
    return 2;
  }

}
