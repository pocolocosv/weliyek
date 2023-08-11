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
package weliyek.amat.newserialization.basic;

import java.io.IOException;

import weliyek.amat.basic.sequence.WkSzSequenceWritingRuntimeControl;
import weliyek.amat.basic.serializer.WkSzPrimitiveArraySerializerWriter;
import weliyek.ketza.util.array.BasicPrimitiveArraySerializationRule;
import weliyek.ketza.util.array.LongArrayWrapper;

public final class LittleEndianLongArrayOutputSerializationRule
    extends LongArrayOutputSerializationRule
{

  public static final BasicPrimitiveArraySerializationRule<LongArrayWrapper> FACTORY =
      new BasicPrimitiveArraySerializationRule<>(
            "L_INT64[]",
            LittleEndianLongArrayOutputSerializationRule::new);

  private LittleEndianLongArrayOutputSerializationRule(
    WkSzSequenceWritingRuntimeControl<?,?,?> runtimeControl,
    WkSzPrimitiveArraySerializerWriter<? extends LongArrayWrapper,?,?,?,?> writingOperation) {
    super(runtimeControl, writingOperation);
  }

  @Override
  protected void writeLong(long l) throws IOException {
    writeLittleEndianLong(l);
  }

}
