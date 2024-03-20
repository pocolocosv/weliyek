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
package weliyek.serialization.tree;

import weliyek.serialization.WkSerdeDTreeNodeDataInput;
import weliyek.serialization.WkSerdeDTreeNodeDataOutput;
import weliyek.serialization.WkSrlzFrameNodeType;

/**
 * Weliyek Serialization and Deserialization Data Tree Node which is the most
 * basic element used to represent and manage data that is to be serialized and
 * deserialized.
 *  
 * There are two different types of trees for a given data type. These are:
 * 
 * <ul>
 *   <li>Structural Node: a tree of objects of type {@link WkSerdeDTreeNodeStruct} that define the
 *       the structure of the type to be serialized including the rules and conditions to be 
 *       followed.</li>
 *   <li>Packet frame: a tree of objects of type {@link WkSerdeDTreeNodeData} that perform the
 *       operations of decoding or encoding of a given data type using the rules defined in
 *       the structural frame tree. A packet frame can be further divided into two other types:</li>
 *   <ul>
 *     <li>Input packet frame: a tree of objects of type {@link WkSerdeDTreeNodeDataInput} that
 *         decodes a given data type from a byte stream.</li>
 *     <li>Output packet frame: a tree of objects of type {@link WkSerdeDTreeNodeDataOutput} that
 *         encode a given data type into a byte stream.</li>
 *   </ul>
 * </ul>
 */
public interface WkSerdeDTreeNode
{

  WkSrlzFrameNodeType type();

}
