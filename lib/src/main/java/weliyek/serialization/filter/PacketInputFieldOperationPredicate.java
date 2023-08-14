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
package weliyek.serialization.filter;

import java.util.function.Predicate;

import weliyek.serialization.WkSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkSrlzInputPacketDecoderFrameNode;

public class PacketInputFieldOperationPredicate<
                        D extends WkSrlzStructDefinitionFrameNode<?,?>,
                        O extends WkSrlzInputPacketDecoderFrameNode<?,?,?,?,D>>
    extends FieldTester<D, O>
{

  public PacketInputFieldOperationPredicate(
      D field, Predicate<? super O> predicate, String description) {
    super(field, predicate, description);
  }

  @Override
  public boolean deserializedIsRequired() {
    return true;
  }

  @Override
  public boolean canBeTestedAgainst(WkSrlzPacketFilterableFrameNode segment) {
    if (isSegmentAReadingOperation(segment)) {
      WkSrlzStructDefinitionFrameNode<?,?> segmentDef = extractDefinition((WkSrlzInputPacketDecoderFrameNode<?,?,?,?,?>)segment);
      return targetProtocolField().equals(segmentDef);
    }
    return false;
  }

}
