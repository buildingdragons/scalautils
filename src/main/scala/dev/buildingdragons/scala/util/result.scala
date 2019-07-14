/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package dev.buildingdragons.scala.util

/**
  * @tparam A the success type
  * @tparam B the failure type
  * @author Hannes Halenka<hannes@transentials.com>
  * @define result  [[dev.buildingdragons.scala.util.Result]]
  * @define success [[dev.buildingdragons.scala.util.Success]]
  * @define failure [[dev.buildingdragons.scala.util.Failure]]
  * @define f       `f`
  */
sealed abstract class Result[+A, +B] {
  /**
    * Applies the provided function to this $success's value.
    *
    * @param f the function to apply
    * @tparam AA the result type of the provided function
    * @return An [[scala.Some]] containing the result of $f applied to the value, if this is a $success. Otherwise a
    *         [[scala.None]] is returned.
    */
  def map[AA](f: A => AA): Option[AA] = {
    this match {
      case r: Success[_, _] => Some(f(r.value))
      case r: Failure[_, _] => None
    }
  }
}

/**
  * Indicates a success result.
  *
  * @param value the expected value
  * @tparam A the success type
  * @tparam B the failure type
  */
final case class Success[+A, +B](value: A) extends Result[A, B]

/**
  * Indicates a failure result
  *
  * @param value a value representing the success
  * @tparam A the success type
  * @tparam B the failure type
  */
final case class Failure[+A, +B](value: B) extends Result[A, B]