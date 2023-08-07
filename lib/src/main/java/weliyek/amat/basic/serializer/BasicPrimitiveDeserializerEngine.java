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
package weliyek.amat.basic.serializer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.basic.number.WkSzNumberReader;
import weliyek.serialization.WkSzOperationException;

public abstract class BasicPrimitiveDeserializerEngine<X extends Number>
    extends InputSerializationEngine<
                        X,
                        ReadingRuntimeControl<?,?,?>,
                        WkSzNumberReader<X,?,?,?,?>>
{

  private static final Logger logger = LoggerFactory.getLogger(BasicPrimitiveDeserializerEngine.class);

  private X deserializedNumber;

  protected BasicPrimitiveDeserializerEngine(
      ReadingRuntimeControl<?,?,?> runtimeCtrl,
      WkSzNumberReader<X,?,?,?,?> readingOperation) {
    super(runtimeCtrl, readingOperation);
  }

  @Override
  protected void onStart() {
    // Nothing to do.
  }

  @Override
  protected void onDone() {
    // Nothing to do.
  }

  @Override
  protected void readBytes() {
      try {
        if (logger.isDebugEnabled()) {
          logger.debug(operation() + " reading " + requestedBytesToIO() + " byte(s)");
        }
        this.deserializedNumber = readNumber();
      } catch (IOException e) {
        throw new WkSzOperationException(operation(), "Failed reading bytes");
      }
  }

  protected abstract X readNumber() throws IOException;

  @Override
  public X getDeserialized() {
    return this.deserializedNumber;
  }

}
