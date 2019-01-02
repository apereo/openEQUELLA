/*
 * Copyright 2019 Apereo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tle.core.migration.impl;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class MigrationConnection implements Connection
{
	private final Connection inner;

	public MigrationConnection(Connection connection)
	{
		this.inner = connection;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException
	{
		return inner.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException
	{
		return inner.isWrapperFor(iface);
	}

	@Override
	public Statement createStatement() throws SQLException
	{
		return inner.createStatement();
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException
	{
		return inner.prepareStatement(sql);
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException
	{
		return inner.prepareCall(sql);
	}

	@Override
	public String nativeSQL(String sql) throws SQLException
	{
		return inner.nativeSQL(sql);
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException
	{
		inner.setAutoCommit(autoCommit);
	}

	@Override
	public boolean getAutoCommit() throws SQLException
	{
		return inner.getAutoCommit();
	}

	@Override
	public void commit() throws SQLException
	{
		inner.commit();
	}

	@Override
	public void rollback() throws SQLException
	{
		inner.rollback();
	}

	@Override
	public void close() throws SQLException
	{
		// inner.close();
	}

	@Override
	public boolean isClosed() throws SQLException
	{
		return inner.isClosed();
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException
	{
		return inner.getMetaData();
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException
	{
		inner.setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() throws SQLException
	{
		return inner.isReadOnly();
	}

	@Override
	public void setCatalog(String catalog) throws SQLException
	{
		inner.setCatalog(catalog);
	}

	@Override
	public String getCatalog() throws SQLException
	{
		return inner.getCatalog();
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException
	{
		inner.setTransactionIsolation(level);
	}

	@Override
	public int getTransactionIsolation() throws SQLException
	{
		return inner.getTransactionIsolation();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException
	{
		return inner.getWarnings();
	}

	@Override
	public void clearWarnings() throws SQLException
	{
		inner.clearWarnings();
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException
	{
		return inner.createStatement(resultSetType, resultSetConcurrency);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
		throws SQLException
	{
		return inner.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException
	{
		return inner.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException
	{
		return inner.getTypeMap();
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException
	{
		inner.setTypeMap(map);
	}

	@Override
	public void setHoldability(int holdability) throws SQLException
	{
		inner.setHoldability(holdability);
	}

	@Override
	public int getHoldability() throws SQLException
	{
		return inner.getHoldability();
	}

	@Override
	public Savepoint setSavepoint() throws SQLException
	{
		return inner.setSavepoint();
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException
	{
		return inner.setSavepoint(name);
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException
	{
		inner.rollback(savepoint);
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException
	{
		inner.releaseSavepoint(savepoint);
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
		throws SQLException
	{
		return inner.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
		int resultSetHoldability) throws SQLException
	{
		return inner.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
		int resultSetHoldability) throws SQLException
	{
		return inner.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException
	{
		return inner.prepareStatement(sql, autoGeneratedKeys);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException
	{
		return inner.prepareStatement(sql, columnIndexes);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException
	{
		return inner.prepareStatement(sql, columnNames);
	}

	@Override
	public Clob createClob() throws SQLException
	{
		return inner.createClob();
	}

	@Override
	public Blob createBlob() throws SQLException
	{
		return inner.createBlob();
	}

	@Override
	public NClob createNClob() throws SQLException
	{
		return inner.createNClob();
	}

	@Override
	public SQLXML createSQLXML() throws SQLException
	{
		return inner.createSQLXML();
	}

	@Override
	public boolean isValid(int timeout) throws SQLException
	{
		return inner.isValid(timeout);
	}

	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException
	{
		inner.setClientInfo(name, value);
	}

	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException
	{
		inner.setClientInfo(properties);
	}

	@Override
	public String getClientInfo(String name) throws SQLException
	{
		return inner.getClientInfo(name);
	}

	@Override
	public Properties getClientInfo() throws SQLException
	{
		return inner.getClientInfo();
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException
	{
		return inner.createArrayOf(typeName, elements);
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException
	{
		return inner.createStruct(typeName, attributes);
	}

	@SuppressWarnings("all")
	public void abort(Executor executor) throws SQLException
	{
		// Can't call inner until we're running on Java 7
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("all")
	public int getNetworkTimeout() throws SQLException
	{
		// Can't call inner until we're running on Java 7
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("all")
	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException
	{
		// Can't call inner until we're running on Java 7
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("all")
	public String getSchema() throws SQLException
	{
		// Can't call inner until we're running on Java 7
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("all")
	public void setSchema(String schema) throws SQLException
	{
		// Can't call inner until we're running on Java 7
		throw new UnsupportedOperationException();
	}
}
