// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/proto/Proto2Test.proto

package com.example.grpc.api;

public final class RPCDateServiceApi {
  private RPCDateServiceApi() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_SearchRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_SearchRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Foo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Foo_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\037src/main/proto/Proto2Test.proto\"L\n\rSea" +
      "rchRequest\022\r\n\005query\030\001 \001(\t\022\023\n\013page_number" +
      "\030\002 \001(\005\022\027\n\017result_per_page\030\003 \001(\005\"!\n\003FooJ\004" +
      "\010\002\020\003J\004\010\017\020\020J\004\010\t\020\014R\003fooR\003barB+\n\024com.exampl" +
      "e.grpc.apiB\021RPCDateServiceApiP\001"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_SearchRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_SearchRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_SearchRequest_descriptor,
        new java.lang.String[] { "Query", "PageNumber", "ResultPerPage", });
    internal_static_Foo_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_Foo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Foo_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}