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

import java.io.IOException;

public abstract class WkSrlzEngineEncoder<
                        T,
                        QC extends WkEncodingRuntimeSrlzPacketOperationCtrl<?,?,?>,
                        O extends WkSerdeDTreeNodeDataWriter<? extends T,?,?,?,?>>
    extends WkSrlzEngine<QC, O>
{

    protected WkSrlzEngineEncoder(QC runtimeControl, O writingOperation) {
      super(runtimeControl, writingOperation);
    }

    protected final T getSerializable() {
      return operation().serializable();
    }

    protected void writeByte(int b) throws IOException {
      getRuntimeControl().writeByte(b);
    }

    protected void writeLittleEndianShort(int s) throws IOException {
      writeByte(s);
      writeByte(s >> 8);
    }

    protected void writeBigEndianShort(int s) throws IOException {
      writeByte(s >> 8);
      writeByte(s);
    }

    protected void writeLittleEndianInt(int i) throws IOException {
      writeByte(i);
      writeByte(i >> 8 * 1);
      writeByte(i >> 8 * 2);
      writeByte(i >> 8 * 3);
    }

    protected void writeBigEndianInt(int i) throws IOException {
      writeByte(i >> 8 * 3);
      writeByte(i >> 8 * 2);
      writeByte(i >> 8 * 1);
      writeByte(i);
    }

    protected void writeLittleEndianLong(long l) throws IOException {
      writeByte((int) l);
      writeByte((int) (l >> 8 * 1));
      writeByte((int) (l >> 8 * 2));
      writeByte((int) (l >> 8 * 3));
      writeByte((int) (l >> 8 * 4));
      writeByte((int) (l >> 8 * 5));
      writeByte((int) (l >> 8 * 6));
      writeByte((int) (l >> 8 * 7));
    }

    protected void writeBigEndianLong(long l) throws IOException {
      writeByte((int) (l >> 8 * 7));
      writeByte((int) (l >> 8 * 6));
      writeByte((int) (l >> 8 * 5));
      writeByte((int) (l >> 8 * 4));
      writeByte((int) (l >> 8 * 3));
      writeByte((int) (l >> 8 * 2));
      writeByte((int) (l >> 8 * 1));
      writeByte((int) l);
    }

}
