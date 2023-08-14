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

/**
 * Weliyek serialization frame node is used to describe the decoding and encoding of a given data
 * type using a tree of objects of this type. A tree of objects is referred as a frame.
 * 
 * There are two different types of trees, i.e. frames, for a given data type. These are:
 * 
 * <ul>
 *   <li>Structural frame: a tree of objects of type {@link WkSrlzStructFrameNode} that define the
 *       the structure of the type to be serialized including the rules and conditions to be 
 *       followed.</li>
 *   <li>Packet frame: a tree of objects of type {@link WkSrlzPacketFrameNode} that perform the
 *       operations of decoding or encoding of a given data type using the rules defined in
 *       the structural frame tree. A packet frame can be further divided into two other types:</li>
 *   <ul>
 *     <li>Input packet frame: a tree of objects of type {@link WkSrlzInputPacketFrameNode} that
 *         decodes a given data type from a byte stream.</li>
 *     <li>Output packet frame: a tree of objects of type {@link WkSrlzOutputPacketFrameNode} that
 *         encode a given data type into a byte stream.</li>
 *   </ul>
 * </ul>
 */
public interface WkSrlzFrameNode
{

  WkSrlzFrameNodeType type();

}
