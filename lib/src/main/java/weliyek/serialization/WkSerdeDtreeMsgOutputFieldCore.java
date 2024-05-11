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

public abstract class WkSerdeDtreeMsgOutputFieldCore<
                        T,
                        YS extends WkSerdeDtreeOperationSettings,
                        YO extends WkSerdeDtreeMsgWriter<T,YS,?,?,YD>,
                        YD extends WkSerdeDtreeStructDefinition<T>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        AYOC extends WkSerdeDtreeMsgWriterCore<?,?,?,?,?,?,?,?,?,?>,
                        YK extends WkSerdeDtreeMsgOutputField<T,YD,YO>,
                        YPC extends WkSerdeDtreeStructFieldCore<
                                        T,?,YO,? extends YD,
                                        ? extends WkSerdeDtreeStructDefinitionCore<T,?,?,?,?,?,?,?,?,YS,?,?,?,?,YO,?,AYBC,? extends YD,?>>>
    extends WkSerdeDtreeMsgFieldCore<
                        T, YS, YD, YO,
                        WkSerdeDtreeMsgWriterCore<?,?,?,?,?,YO,?,?,?,?>,
                        YK, AYBC, AYOC, YPC>
    implements WkSerdeDtreeMsgOutputField<T,YD,YO>
{

  public WkSerdeDtreeMsgOutputFieldCore(
    YPC structFieldCore,
    boolean enabled) {
    super(
        structFieldCore,
        WkSerdeDtreeMsgWriter::serializable,
        enabled);
  }

  @Override
  protected WkSerdeDtreeMsgWriterCore<?,?,?,?,?,YO,?,?,?,?>
  newOperation(int index) {
    T serializable = serializable(index);
    YS settings = newSettings(index);
    AYBC parentBytestream = parentBytestream();
    return structFieldCore().definitionCore().newWritingOperationCore(
        index, settings, parentBytestream, serializable, this);
  }

  @Override
  protected abstract YS newSettings(int index);

  @Override
  protected abstract AYBC parentBytestream();

  protected abstract T serializable(int index);

  @Override
  protected void onDoneProcessing() {
    // Nothing to do
  }

  @Override
  public WkSerdeDtreeStructField<? extends YD> structComponent() {
    return structFieldCore().asProtocolField();
  }

}
