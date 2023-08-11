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
import weliyek.ketza.util.array.ShortArrayWrapper;

public final class BigEndianShortArrayOutputRule
    extends ShortArrayOutputSerializationRuleBase
{

  public static final BasicPrimitiveArraySerializationRule<ShortArrayWrapper> FACTORY =
      new BasicPrimitiveArraySerializationRule<>(
            "B_INT16[]",
            BigEndianShortArrayOutputRule::new);

  private BigEndianShortArrayOutputRule(
    WkSzSequenceWritingRuntimeControl<?,?,?> runtimeControl,
    WkSzPrimitiveArraySerializerWriter<? extends ShortArrayWrapper,?,?,?,?> writingOperation) {
    super(runtimeControl, writingOperation);
  }

  @Override
  protected void writeShort(int s) throws IOException {
    writeBigEndianShort(s);
  }

}
