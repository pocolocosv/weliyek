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

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.function.Function;

import weliyek.serialization.WkSerdeDtreeNodeDataInputComponentCoreRoot.ReadingPacketParameters;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponentCoreRoot.WritingParameters;
import weliyek.serialization.filter.WkSrlzFilter;

/**
 * Main type that holds all components that form a self contained struct which defines
 * how a <code><strong>T</strong></code> object is serialized.
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
public final class WkSerdeDtreeNodeStructComponentCoreRoot<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XD extends WkSerdeDtreeNodeStructDefinition<T>,
                        XO extends WkSerdeDtreeNodeDataReader<T,XS,?,?,XD>,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YD extends WkSerdeDtreeNodeStructDefinition<T>,
                        YO extends WkSerdeDtreeNodeDataWriter<T,YS,?,?,YD>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        D extends WkSerdeDtreeNodeStructDefinition<T>>
    extends WkSerdeDtreeNodeStructComponentCore<
                        T, XS, XD, XO, AXBC,
                        YS, YD, YO, AYBC,
                        D>
    implements WkSerdeDtreeStruct<T, XS, XD, XO, AXBC, YS, YD, YO, AYBC, D>
{

  private final Function<InputStream, AXBC> inputbytestreamFactory;
  private final Function<OutputStream, AYBC> outputbytestreamFactory;

  public WkSerdeDtreeNodeStructComponentCoreRoot(
    String label,
    WkSrlzStructDefinitionFrameNodeCoreFactory<T,XS,XD,XO,AXBC,YS,YD,YO,AYBC,D> definitionFactory,
    Function<InputStream, AXBC> inputbytestreamFactory,
    Function<OutputStream, AYBC> outputbytestreamFactory) {
    super(label, definitionFactory);
    this.inputbytestreamFactory = Objects.requireNonNull(inputbytestreamFactory);
    this.outputbytestreamFactory = Objects.requireNonNull(outputbytestreamFactory);
  }

  @Override
  public WkSzInputPacket<T, XD, XO>
  newInputPacket(XS settings, InputStream inputstream) {
    return newInputPacket(settings, inputstream, WkSrlzFilter.getEmptyFilter());
  }

  @Override
  public WkSzInputPacket<T, XD, XO>
  newInputPacket(XS settings, InputStream inputstream, WkSrlzFilter filter) {
    AXBC inBytestream = inputbytestreamFactory.apply(inputstream);
    return newInputPacket(settings, inBytestream, filter);
  }

  @Override
  public WkSzInputPacket<T, XD, XO>
  newInputPacket(XS settings, AXBC inputBytestream) {
    return newInputPacket(settings, inputBytestream, WkSrlzFilter.getEmptyFilter());
  }

  @Override
  public WkSzInputPacket<T, XD, XO>
  newInputPacket(XS settings, AXBC inputBytestream, WkSrlzFilter filter) {
    ReadingPacketParameters<XS, AXBC> params = new WkSerdeDtreeNodeDataInputComponentCoreRoot.ReadingPacketParameters<XS, AXBC>(
                                                                        settings,
                                                                        inputBytestream,
                                                                        filter);
    @SuppressWarnings("unchecked")
    WkSerdeDtreeNodeDataInputComponentCoreRoot<T,XS,XD,XO,AXBC> reading = new WkSerdeDtreeNodeDataInputComponentCoreRoot<T,XS,XD,XO,AXBC>(
        (WkSerdeDtreeNodeStructComponentCore<T,XS,XD,XO,AXBC,?,?,?,?,? extends XD>) this, params);
    reading.initialize(true);
    return reading.asPacket();
  }

  @Override
  public WkSzOutputPacket<T, YD, YO>
  newOutputPacket(T serializable, YS settings, OutputStream outputstream)  {
    AYBC outBytestream = outputbytestreamFactory.apply(outputstream);
    return newOutputPacket(serializable, settings, outBytestream, WkSrlzFilter.getEmptyFilter());
  }

  @Override
  public WkSzOutputPacket<T, YD, YO>
  newOutputPacket(T serializable, YS settings, AYBC outputBytestream)  {
    return newOutputPacket(serializable, settings, outputBytestream, WkSrlzFilter.getEmptyFilter());
  }

  @Override
  public WkSzOutputPacket<T, YD, YO>
  newOutputPacket(T serializable, YS settings, AYBC outputBytestream, WkSrlzFilter filter)  {
    WritingParameters<T,YS,AYBC> writingParams = new WkSerdeDtreeNodeDataOutputComponentCoreRoot.WritingParameters<T,YS,AYBC>(
                                                                        serializable,
                                                                        settings,
                                                                        outputBytestream);
    @SuppressWarnings("unchecked")
    WkSerdeDtreeNodeDataOutputComponentCoreRoot<T,YS,YD,YO,AYBC> writing = new WkSerdeDtreeNodeDataOutputComponentCoreRoot<T,YS,YD,YO,AYBC>(
        (WkSerdeDtreeNodeStructComponentCore<T,?,?,?,?,YS,YD,YO,AYBC,? extends YD>) this, writingParams);
    writing.initialize(true);
    return writing.asPacket();
  }

  @Override
  protected WkSerdeDtreeNodeStructDefinitionCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore() {
    return null;
  }

}
