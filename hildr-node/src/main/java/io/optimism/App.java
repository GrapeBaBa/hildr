/*
 * Copyright 2023 281165273grape@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.optimism;

/**
 * The type App.
 *
 * @author grapebaba
 * @since 0.1.0
 */
public class App {

  /** Instantiates a new App. */
  public App() {}

  /**
   * Gets greeting.
   *
   * @return the greeting
   */
  public String getGreeting() {
    return "Hello World!";
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    System.out.println(new App().getGreeting());
  }
}
