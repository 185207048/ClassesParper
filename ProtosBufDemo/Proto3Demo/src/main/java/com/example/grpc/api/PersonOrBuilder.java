// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/proto/Proto3Test.proto

package com.example.grpc.api;

public interface PersonOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Person)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>int32 id = 2;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>bool has_ponycopter = 3;</code>
   * @return The hasPonycopter.
   */
  boolean getHasPonycopter();
}
