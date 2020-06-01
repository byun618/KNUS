module.exports = (sequelize, DataTypes) => {
    return sequelize.define('chat', {

        chat_id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            allowNull: false
        },
        grade: {
            type: DataTypes.INTEGER,
            allowNull: false
        }
    },{
        timestamps: false,
        tableName: 'chat'
    })
}