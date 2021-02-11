package org.ww.flink

import org.apache.flink.core.fs.Path
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

import collection.JavaConverters._

object DummyFileSink {
  def main(args: Array[String]): Unit = {
    import org.apache.flink.api.common.serialization.SimpleStringEncoder
    import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink
    val outputPath = "./output/"
    val sink = StreamingFileSink.forRowFormat(new Path(outputPath), new SimpleStringEncoder[String]("UTF-8")).build
    val env = StreamExecutionEnvironment.getExecutionEnvironment()
    env.fromCollection(List("a", "b").asJava)
      .addSink(sink)
    env.execute()
  }
}
