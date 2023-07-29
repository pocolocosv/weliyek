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
package weliyek.amat.base;

import java.util.Objects;
import java.util.function.Predicate;

import weliyek.amat.base.input.DeserializingOperation;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.SerializingOperation;
import weliyek.amat2.protocol.filter.FieldTester;

public abstract class ComponentSegmentCore<
                        T,
                        XS extends OperationSettings,
                        XD extends DefinitionSegment<T,?>,
                        XO extends DeserializingOperation<T,XS,?,?,XD>,
                        AXBC extends InputBytestreamGeneralBase<?>,
                        YS extends OperationSettings,
                        YD extends DefinitionSegment<T,?>,
                        YO extends SerializingOperation<T,YS,?,?,YD>,
                        AYBC extends OutputBytestreamGeneralBase<?>,
                        D extends DefinitionSegment<T,?>>
    implements ComponentSegment<D>
{

  public static final char FIELD_NAME_SEPARATOR = '.';

  private final String label;
  private final DefinitionSegmentCore<T,XS,?,?,XD,XO,AXBC,YS,?,?,YD,YO,AYBC,D,?> definitionCore;

  private String name;

  private final ComponentSegment<D> body;

  protected ComponentSegmentCore(
    String label,
    ProtocolDefinitionFactory<T,XS,XD,XO,AXBC,YS,YD,YO,AYBC,D> definitionFactory) {
    this.label = Objects.requireNonNull(label);
    this.body = new ComponentSegment<D>() {

      @Override
      public String label() {
        return ComponentSegmentCore.this.label();
      }

      @Override
      public D definition() {
        return ComponentSegmentCore.this.definition();
      }

      @Override
      public String name() {
        return ComponentSegmentCore.this.name();
      }

      @Override
      public FieldTester<?, ?>
          makeTester(Predicate<FieldSegment<?, ?, ?>> test, String description) {
        return ComponentSegmentCore.this.makeTester(test, description);
      }
    };
    this.definitionCore = Objects.requireNonNull(definitionFactory).apply(this);
    this.definitionCore.initialize();
  }

  @Override
  public final String label() {
    return this.label;
  }

  public ComponentSegment<D> asProtocolField() {
    return this.body;
  }

  protected abstract DefinitionSegmentCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore();

  @Override
  public D definition() {
    return definitionCore().definition();
  }

  public DefinitionSegmentCore<T,XS,?,?,XD,XO,AXBC,YS,?,?,YD,YO,AYBC,D,?> definitionCore() {
    return this.definitionCore;
  }

  private String generateName() {
    StringBuilder strB = new StringBuilder();
    if (null == parentDefinitionCore()) {
      strB.append(asProtocolField().type().name());
      strB.append('(');
      strB.append(hashCode());
      strB.append(')');
      strB.append(':');
      strB.append(label());
      return  strB.toString();
    } else {
      strB.append(parentDefinitionCore().name());
      strB.append(ComponentSegmentCore.FIELD_NAME_SEPARATOR);
      strB.append(label());
      return strB.toString();
    }
  }

  @Override
  public String name() {
    if (null == this.name) {
      this.name = generateName();
    }
    return this.name;
  }

  @Override
  public String toString() {
    return name();
  }

  @Override
  public FieldTester<?, ?> makeTester(Predicate<FieldSegment<?, ?, ?>> test, String description) {
    // TODO Auto-generated method stub
    return null;
  }

}
