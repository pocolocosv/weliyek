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
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import weliyek.serialization.WkSerdeDtreeReaderCore.ReadingPacketParameters;
import weliyek.serialization.WkSerdeDtreeWriterCore.WritingParameters;
import weliyek.serialization.filter.WkSrlzFilter;
import weliyek.serialization.filter.WkSrlzPacketNodePredicate;

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
public final class WkSerdeDtreeStructCore<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XD extends WkSerdeDtreeStructDefinition<T>,
                        XO extends WkSerdeDtreeMsgReader<T,XS,?,?,XD>,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YD extends WkSerdeDtreeStructDefinition<T>,
                        YO extends WkSerdeDtreeMsgWriter<T,YS,?,?,YD>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        D extends WkSerdeDtreeStructDefinition<T>>
    extends WkSerdeDtreeStructFieldCore<
                        T,XO,YO,D,
                        WkSerdeDtreeStructDefinitionCore<T,XS,?,?,XD,?,XO,?,AXBC,YS,?,?,YD,?,YO,?,AYBC,D,?>>
    implements WkSerdeDtreeStruct<T, XS, XD, XO, AXBC, YS, YD, YO, AYBC, D>
{

  private final Function<InputStream, AXBC> inputbytestreamFactory;
  private final Function<OutputStream, AYBC> outputbytestreamFactory;
  private WkSerdeDtreeStruct<T, XS, XD, XO, AXBC, YS, YD, YO, AYBC, D> body;

  public WkSerdeDtreeStructCore(
    String label,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ? extends WkSerdeDtreeStructDefinitionCore<T,XS,?,?,XD,?,XO,?,AXBC,YS,?,?,YD,?,YO,?,AYBC,D,?>> definitionFactory,
    Function<InputStream, AXBC> inputbytestreamFactory,
    Function<OutputStream, AYBC> outputbytestreamFactory) {
    super(label, definitionFactory);
    this.inputbytestreamFactory = Objects.requireNonNull(inputbytestreamFactory);
    this.outputbytestreamFactory = Objects.requireNonNull(outputbytestreamFactory);
    this.body = new WkSerdeDtreeStruct<T, XS, XD, XO, AXBC, YS, YD, YO, AYBC, D>() {

      @Override
      public String label() {
        return WkSerdeDtreeStructCore.this.label();
      }

      @Override
      public D definition() {
        return WkSerdeDtreeStructCore.this.definition();
      }

      @Override
      public String name() {
        return WkSerdeDtreeStructCore.this.name();
      }

      @Override
      public WkSrlzPacketNodePredicate<?, ?> makeTester(
        Predicate<WkSerdeDtreeMsgField<?, ?, ? super D>> test,
        String description) {
        return WkSerdeDtreeStructCore.this.makeTester(test, description);
      }

      @Override
      public boolean isRxRequired() {
        return WkSerdeDtreeStructCore.this.isRxRequired();
      }

      @Override
      public boolean isRxOptional() {
        return WkSerdeDtreeStructCore.this.isRxOptional();
      }

      @Override
      public boolean isTxRequired() {
        return WkSerdeDtreeStructCore.this.isTxRequired();
      }

      @Override
      public boolean isTxOptional() {
        return WkSerdeDtreeStructCore.this.isTxOptional();
      }

      @Override
      public WkSerdeDtreeReader<T, XD, XO> newInputPacket(XS settings, InputStream inputstream) {
        return WkSerdeDtreeStructCore.this.newInputPacket(settings, inputstream);
      }

      @Override
      public WkSerdeDtreeReader<T, XD, XO>
          newInputPacket(XS settings, InputStream inputstream, WkSrlzFilter filter) {
        return WkSerdeDtreeStructCore.this.newInputPacket(settings, inputstream, filter);
      }

      @Override
      public WkSerdeDtreeReader<T, XD, XO> newInputPacket(XS settings, AXBC inputBytestream) {
        return WkSerdeDtreeStructCore.this.newInputPacket(settings, inputBytestream);
      }

      @Override
      public WkSerdeDtreeReader<T, XD, XO>
          newInputPacket(XS settings, AXBC inputBytestream, WkSrlzFilter filter) {
        return WkSerdeDtreeStructCore.this.newInputPacket(settings, inputBytestream, filter);
      }

      @Override
      public WkSerdeDtreeWriter<T, YD, YO>
          newOutputPacket(T serializable, YS settings, OutputStream outputstream) {
        return WkSerdeDtreeStructCore.this.newOutputPacket(serializable, settings, outputstream);
      }

      @Override
      public WkSerdeDtreeWriter<T, YD, YO>
          newOutputPacket(T serializable, YS settings, AYBC outputBytestream) {
        return WkSerdeDtreeStructCore.this.newOutputPacket(serializable, settings, outputBytestream);
      }

      @Override
      public WkSerdeDtreeWriter<T, YD, YO>
          newOutputPacket(T serializable, YS settings, AYBC outputBytestream, WkSrlzFilter filter) {
        return WkSerdeDtreeStructCore.this.newOutputPacket(serializable, settings, outputBytestream, filter);
      }

    };
  }

  @Override
  public WkSerdeDtreeReader<T, XD, XO>
  newInputPacket(XS settings, InputStream inputstream) {
    return newInputPacket(settings, inputstream, WkSrlzFilter.getEmptyFilter());
  }

  @Override
  public WkSerdeDtreeReader<T, XD, XO>
  newInputPacket(XS settings, InputStream inputstream, WkSrlzFilter filter) {
    AXBC inBytestream = inputbytestreamFactory.apply(inputstream);
    return newInputPacket(settings, inBytestream, filter);
  }

  @Override
  public WkSerdeDtreeReader<T, XD, XO>
  newInputPacket(XS settings, AXBC inputBytestream) {
    return newInputPacket(settings, inputBytestream, WkSrlzFilter.getEmptyFilter());
  }

  @Override
  public WkSerdeDtreeReader<T, XD, XO>
  newInputPacket(XS settings, AXBC inputBytestream, WkSrlzFilter filter) {
    ReadingPacketParameters<XS, AXBC> params = new WkSerdeDtreeReaderCore.ReadingPacketParameters<XS, AXBC>(
                                                                        settings,
                                                                        inputBytestream,
                                                                        filter);
    @SuppressWarnings("unchecked")
    WkSerdeDtreeReaderCore<T,XS,XD,XO,AXBC> reading = new WkSerdeDtreeReaderCore<T,XS,XD,XO,AXBC>(
        (WkSerdeDtreeStructCore<T, XS, XD, XO, AXBC, ?, ?, ?, ?, ? extends XD>) this, params);
    reading.initialize();
    return reading.asPacket();
  }

  @Override
  public WkSerdeDtreeWriter<T, YD, YO>
  newOutputPacket(T serializable, YS settings, OutputStream outputstream)  {
    AYBC outBytestream = outputbytestreamFactory.apply(outputstream);
    return newOutputPacket(serializable, settings, outBytestream, WkSrlzFilter.getEmptyFilter());
  }

  @Override
  public WkSerdeDtreeWriter<T, YD, YO>
  newOutputPacket(T serializable, YS settings, AYBC outputBytestream)  {
    return newOutputPacket(serializable, settings, outputBytestream, WkSrlzFilter.getEmptyFilter());
  }

  @Override
  public WkSerdeDtreeWriter<T, YD, YO>
  newOutputPacket(T serializable, YS settings, AYBC outputBytestream, WkSrlzFilter filter)  {
    WritingParameters<T,YS,AYBC> writingParams = new WkSerdeDtreeWriterCore.WritingParameters<T,YS,AYBC>(
                                                                        serializable,
                                                                        settings,
                                                                        outputBytestream);
    @SuppressWarnings("unchecked")
    WkSerdeDtreeWriterCore<T,YS,YD,YO,AYBC> writing = new WkSerdeDtreeWriterCore<T,YS,YD,YO,AYBC>(
        (WkSerdeDtreeStructCore<T,?,?,?,?,YS,YD,YO,AYBC,? extends YD>) this, writingParams);
    writing.initialize();;
    return writing.asPacket();
  }

  @Override
  protected Optional<WkSerdeDtreeStructDefinitionCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?>> parentDefinitionCore() {
    return Optional.empty();
  }

  @Override
  public final boolean isRxRequired() {
    return true;
  }

  @Override
  public final boolean isRxOptional() {
    return !isRxRequired();
  }

  @Override
  public final boolean isTxRequired() {
    return true;
  }

  @Override
  public final boolean isTxOptional() {
    return !isTxRequired();
  }

  @Override
  public WkSerdeDtreeStruct<T, XS, XD, XO, AXBC, YS, YD, YO, AYBC, D> asProtocolField() {
    return this.body;
  }

}
