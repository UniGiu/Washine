package washine_db.groups;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import washine_db.jooq.generated.tables.Groups;
import washine_db.jooq.generated.tables.Groupuserslist;
import washine_db.jooq.generated.tables.Invites;
import washine_db.jooq.generated.tables.records.GroupuserslistRecord;
import washine_db.jooq.generated.tables.records.InvitesRecord;
import washine_db.washine_db.JOOQCodeGeneration;

/** Group class used to interact with the database */
public class WashineGroupDb implements WashineGroupDbIf {
	public WashineGroupDb() {
		/* this constructor is empty */
	};

	/**
	 * Creates a new group/community tuple in the database
	 *
	 * @throws SQLException
	 * @param userId    id of the group's creator
	 * @param groupName name of the newly created group
	 * @return true if only one tuple is added
	 */
	public boolean createGroup(String userId, String groupName) throws SQLException {
		Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
		DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
		int result = create.insertInto(Groups.GROUPS).values(userId, groupName).execute();

		return result == 1;
	}

	/**
	 * Adds a participation to a group/community in the database
	 *
	 * @throws SQLException
	 * @param laundryPersonId id of the group's creator
	 * @param participantId   id the person trying to join the group
	 * @param participantName name of the participant which is given by the laundry
	 *                        person to recognize his group's participants
	 * @return true if only one tuple is added
	 */
	public boolean addParticipationToGroup(String laundryPersonId, String participantId, String participantName)
			throws SQLException {
		Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
		DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
		int result = create.insertInto(Groupuserslist.GROUPUSERSLIST)
				.values(laundryPersonId, participantId, participantName).execute();

		return result == 1;
	}

	/**
	 * Adds an invitation to a group/community in the database
	 *
	 * @throws SQLException
	 * @param laundryPersonId id of the person who invited another person to join
	 *                        his group
	 * @param invitedName     name of the person invited (which is given by the
	 *                        laundry person to recognize) his group's participants
	 * @param code            the invitation's code
	 */
	public void addInvite(String laundryPersonId, String invitedName, String code) throws SQLException {
		Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
		DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

		create.insertInto(Invites.INVITES).values(laundryPersonId, invitedName, code).execute();
	}

	/**
	 * Removes an invitation to a group/community from the database through the
	 * participant name
	 *
	 * @throws SQLException
	 * @param particiapantName name of the person whose invitation you want to
	 *                         revoke
	 * @return true if only one tuple is removed
	 */
	public boolean removeInvite(String participantName) throws SQLException {
		Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
		DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
		int result = create.deleteFrom(Invites.INVITES).where(Invites.INVITES.INVITEDNAME.eq(participantName))
				.execute();

		return result == 1;
	}

	/**
	 * Selects the group's participant from the database, given the id of the person
	 * who created the group
	 *
	 * @throws SQLException
	 * @param laundryPersonId id of the group's creator
	 * @return the list of participants' names
	 */
	public List<String> getGroupParticipants(String laundryPersonId) throws SQLException {
		Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
		DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
		return create.select().from(Groupuserslist.GROUPUSERSLIST)
				.where(Groupuserslist.GROUPUSERSLIST.LAUNDRYPERSONID.eq(laundryPersonId))
				.fetch(Groupuserslist.GROUPUSERSLIST.NAME);
	}

	/**
	 * Selects the groups/communities that a person joined from the database
	 *
	 * @throws SQLException
	 * @param participantId id of the person who joined the group/s
	 * @return the list of groups'name
	 */
	public List<String> getParticipatedGroups(String participantId) throws SQLException {
		Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
		DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

		return create.select().from(Groups.GROUPS).join(Groupuserslist.GROUPUSERSLIST)
				.on(Groups.GROUPS.USERID.eq(Groupuserslist.GROUPUSERSLIST.LAUNDRYPERSONID))
				.where(Groupuserslist.GROUPUSERSLIST.PARTICIPANTID.eq(participantId)).fetch(Groups.GROUPS.GROUPNAME);
	}

	/**
	 * Selects the list of invitations to a group that a person has created from the
	 * database
	 *
	 * @throws SQLException
	 * @param laundryPersonId id of the person who created the invitations
	 * @return the records containing the invitations
	 */
	public Result<InvitesRecord> getInvites(String laundryPersonId) throws SQLException {
		Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
		DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
		return create.selectFrom(Invites.INVITES).where(Invites.INVITES.LAUNDRYPERSONID.eq(laundryPersonId)).fetch();
		/*
		 * to fetch the single tuple for(InvitesRecord r1 : result) { r1.g }
		 */
	}

	/**
	 * Removes a person from a given group through his id from the database
	 *
	 * @throws SQLException
	 * @param participantId id of the person you want to remove from the group
	 * @return true if only one tuple is removed
	 */
	public boolean removeGroupMember(String participantId) throws SQLException {
		Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
		DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
		int result = create.deleteFrom(Groupuserslist.GROUPUSERSLIST)
				.where(Groupuserslist.GROUPUSERSLIST.PARTICIPANTID.eq(participantId)).execute();

		return result == 1;
	}

	/**
	 * Checks if an invite with a given name is already present in the database
	 *
	 * @throws SQLException
	 * @param laundryPersonId id of the group's creator
	 * @param invitedName     name of the invited person
	 * @return true if the person has already been invited or false if he has not
	 *         been invited yet
	 */
	public boolean alreadyInvited(String laundryPersonId, String invitedName) throws SQLException {
		Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
		DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

		Result<InvitesRecord> invites = create.selectFrom(Invites.INVITES).where(
				Invites.INVITES.LAUNDRYPERSONID.eq(laundryPersonId).and(Invites.INVITES.INVITEDNAME.eq(invitedName)))
				.fetch();
		return invites.isNotEmpty();
	}

	/**
	 * Checks if a user has already been added to a group in the database by
	 * checking the group's users list
	 *
	 * @throws SQLException
	 * @param laundryPersonId id of the group's creator
	 * @param participantId   name of the user whose presence in the group you want
	 *                        to check
	 * @return true if the person has already been added to the group or false if he
	 *         has not been added yet
	 */
	public boolean alreadyAdded(String laundryPersonId, String participantId) throws SQLException {
		Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
		DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

		Result<GroupuserslistRecord> usersList = create
				.selectFrom(Groupuserslist.GROUPUSERSLIST).where(Groupuserslist.GROUPUSERSLIST.LAUNDRYPERSONID
						.eq(laundryPersonId).and(Groupuserslist.GROUPUSERSLIST.PARTICIPANTID.eq(participantId)))
				.fetch();
		return !usersList.isEmpty();
	}

	/**
	 * Updates the code of a group invitation in the database
	 *
	 * @throws SQLException
	 * @param invitedName name of the person you want to change the invitation code
	 *                    of
	 * @param newCode     the new invitation code
	 * @return true if only one tuple is added
	 */
	public boolean updateCode(String invitedName, String newCode) throws SQLException {
		Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
		DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

		int result = create.update(Invites.INVITES).set(Invites.INVITES.CODE, newCode)
				.where(Invites.INVITES.INVITEDNAME.eq(invitedName)).execute();
		return result == 1;
	}

	/**
	 * Selects a laundryPersonId (group's creator) given the unique invitation code
	 * that he created
	 *
	 * @throws SQLException
	 * @param code code of the invitation
	 * @return the laundry person id or null if no id correspond to the code
	 */
	public String getLaundryPersonId(String code) throws SQLException {
		Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
		DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

		Record1<String> id = create.select(Invites.INVITES.LAUNDRYPERSONID).from(Invites.INVITES)
				.where(Invites.INVITES.CODE.eq(code)).fetchOne();
		return id.getValue(Invites.INVITES.CODE);
	}

	public boolean nameInCommunity(String name, String communityUid) throws SQLException {
		Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
		DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

		Result<GroupuserslistRecord> names = create.selectFrom(Groupuserslist.GROUPUSERSLIST)
				.where(Groupuserslist.GROUPUSERSLIST.LAUNDRYPERSONID.eq(communityUid)
						.and(Groupuserslist.GROUPUSERSLIST.NAME.eq(name)))
				.fetch();
		return names.isNotEmpty();
	}

	public String getInvitationName(String code) throws SQLException {
		Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
		DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
		Record1<String> name = create.select(Invites.INVITES.INVITEDNAME).from(Invites.INVITES)
				.where(Invites.INVITES.CODE.eq(code)).fetchOne();

		return name.getValue(Invites.INVITES.INVITEDNAME);
	}

	public boolean clearExpiredInvitations(Integer timeToLive){
	  try {
		  
	  
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    Integer secondsToLive=timeToLive*60*60*24;
    long unixTimestamp = Instant.now().getEpochSecond();
   
        create
            .delete(Invites.INVITES)
            .where(Invites.INVITES.TS.le((int) (unixTimestamp-secondsToLive)));
        return true;
	  }catch(SQLException exc) {
		  return false;
	  }
  }
}
