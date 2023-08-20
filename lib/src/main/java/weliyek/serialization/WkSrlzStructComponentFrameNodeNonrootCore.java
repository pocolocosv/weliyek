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

import java.util.Objects;

/**
 * Defines a struct component that has other components as a parent. Only the sibling 
 * type {@link WkSrlzStructComponentFrameNodeRootCore} does not have a parent.
 * 
 * @param <T>
 * @param <XS>
 * @param <XD>
 * @param <XO>
 * @param <AXBC>
 * @param <YS>
 * @param <YD>
 * @param <YO>
 * @param <AYBC>
 * @param <D>
 */
public class WkSrlzStructComponentFrameNodeNonrootCore<
                        T,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XD extends WkSrlzStructDefinitionFrameNode<T>,
                        XO extends WkSrlzInputPacketDecoderFrameNode<T,XS,?,?,XD>,
                        AXBC extends WkSzInputBytestreamBase<?>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YD extends WkSrlzStructDefinitionFrameNode<T>,
                        YO extends WkSrlzOutputPacketEncoderFrameNode<T,YS,?,?,YD>,
                        AYBC extends WkSzOutputBytestreamBase<?>,
                        D extends WkSrlzStructDefinitionFrameNode<T>>
    extends WkSrlzStructComponentFrameNodeCore<T, XS, XD, XO, AXBC, YS, YD, YO, AYBC, D>
{

  private final WkSrlzStructDefinitionFrameNodeCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore;

  WkSrlzStructComponentFrameNodeNonrootCore(
    String label,
    WkSrlzStructDefinitionFrameNodeCoreFactory<T,XS,XD,XO,AXBC,YS,YD,YO,AYBC,D> definitionFactory,
    WkSrlzStructDefinitionFrameNodeCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore) {
    super(label, definitionFactory);
    this.parentDefinitionCore = Objects.requireNonNull(parentDefinitionCore);
  }

  @Override
  protected WkSrlzStructDefinitionFrameNodeCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore() {
    return this.parentDefinitionCore;
  }

}
