package com.tle.core.db

import com.tle.core.db.migration.DBSchemaMigration
import com.tle.core.db.tables.AuditLogEntry
import com.tle.core.db.types.JsonColumn
import com.tle.core.migration.MigrationResult
import fs2.Stream
import io.doolse.simpledba.Iso
import io.doolse.simpledba.jdbc._
import io.doolse.simpledba.syntax._
import io.doolse.simpledba.jdbc.oracle._
import shapeless.HList
import shapeless.syntax.singleton._

object OracleSchema extends DBSchemaMigration with DBSchema with DBQueries {

  implicit val config = oracleConfig
  val schemaSQL = config.schemaSQL

  val hibSeq = Sequence[Long]("hibernate_sequence")

  implicit def jsonColumns[A <: JsonColumn](implicit c: Iso[A, Option[String]]): OracleColumn[A] = {
    OracleColumn(StdJDBCColumn.optionalColumn(StdJDBCColumn.stringCol).isoMap(c), OracleColumn.oracleString.columnType)
  }

  val auditLog = TableMapper[AuditLogEntry].table("audit_log_entry").key('id)
  val userAndInst = auditLog.cols(HList('user_id.narrow, 'institution_id.narrow))
  val auditLogQueries = AuditLogQueries(insertWith(auditLog, hibSeq),
    auditLog.delete.where(userAndInst, EQ).build,
    auditLog.query.whereEQ(userAndInst).build)

  val auditLogTable = auditLog.definition

  val auditLogIndexColumns : TableColumns = auditLog.subsetOf('institution_id, 'timestamp, 'event_category, 'event_type,
    'user_id, 'session_id, 'data1, 'data2, 'data3)

  val auditLogNewColumns = auditLog.subset('meta)

  def addColumns(columns: TableColumns, progress: MigrationResult): JDBCIO[Unit] = {
    progress.setCanRetry(true)
    Stream.emits(schemaSQL.addColumns(columns).map(rawSQL)).covary[JDBCIO].flush.compile.drain
  }
}
