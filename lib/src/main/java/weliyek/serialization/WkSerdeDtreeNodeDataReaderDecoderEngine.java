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
package weliyek.serialization;

import java.io.EOFException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WkSerdeDtreeNodeDataReaderDecoderEngine<
                        X,
                        QC extends WkDecodingRuntimeSrlzPacketOperationCtrl<?,?,?>,
                        O extends WkSerdeDtreeNodeDataReader<X,?,?,?,?>>
    extends WkSerdeDtreeNodeDataEncoderDecoderEngine<QC,O>
{

  private static final Logger logger = LoggerFactory.getLogger(WkSerdeDtreeNodeDataReaderDecoderEngine.class);

  protected WkSerdeDtreeNodeDataReaderDecoderEngine(
    QC runtimeControl,
    O readingOperation) {
    super(runtimeControl, readingOperation);
  }

  @Override
  protected void onBytesLeftToProcess() {
    if(operation().packetField().deserializedRequired()) {
      readBytes();
    } else {
      skipBytes();
    }
  }

  private void skipBytes() {
    long skippedBytes;
    try {
      if (logger.isDebugEnabled()) {
        logger.debug(operation().name() + " skipping bytes");
      }
      skippedBytes = getRuntimeControl().skipBytes(requestedBytesToIO());
    } catch (IOException e) {
      throw new WkSerdeDtreeNodeDataOperationException(operation(), "Skipping bytes failed");
    }
    if (requestedBytesToIO() != skippedBytes) {
      throw new WkSerdeDtreeNodeDataOperationException(operation(), "Skipping " + requestedBytesToIO()
                                                + " bytes during deserializing failed (only skipped "
                                                + skippedBytes + " bytes)");
    }
  }

  protected abstract void readBytes();

  public abstract X getDeserialized();

  protected int readByte() throws IOException {
    int b = getRuntimeControl().readByte();
    if (-1 == b) throw new EOFException();
    return b;
  }

  protected int readLittleEndianShort() throws IOException {
    int s = readByte();
    s |= readByte() << 8;
    return s;
  }

  protected int readBigEndianShort() throws IOException {
    int s;
    s = readByte() << 8;
    s |= readByte();
    return s;
  }

  protected int readLittleEndianInt() throws IOException {
    int i;
    i = readByte();
    i |= readByte() << 8;
    i |= readByte() << 16;
    i |= readByte() << 24;
    return i;
  }

  protected int readBigEndianInt() throws IOException {
    int i;
    i = readByte() << 8 * 3;
    i |= readByte() << 8 * 2;
    i |= readByte() << 8 * 1;
    i |= readByte();
    return i;
  }

  protected long readLittleEndianLong() throws IOException {
    long l;
    l  = readByte();
    l |= (long) readByte() << 8 * 1;
    l |= (long) readByte() << 8 * 2;
    l |= (long) readByte() << 8 * 3;
    l |= (long) readByte() << 8 * 4;
    l |= (long) readByte() << 8 * 5;
    l |= (long) readByte() << 8 * 6;
    l |= (long) readByte() << 8 * 7;
    return l;
  }

  protected long readBigEndianLong() throws IOException {
    long l;
    l = (long) readByte() << 8 * 7;
    l |= (long) readByte() << 8 * 6;
    l |= (long) readByte() << 8 * 5;
    l |= (long) readByte() << 8 * 4;
    l |= (long) readByte() << 8 * 3;
    l |= (long) readByte() << 8 * 2;
    l |= (long) readByte() << 8 * 1;
    l |= readByte();
    return l;
  }

}
