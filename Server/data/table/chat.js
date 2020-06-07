module.exports = (sequelize, DataTypes) => {
    return sequelize.define('chat', {

        chat_id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            allowNull: false
        },
        student_id: {
            type: DataTypes.STRING(10),
            allowNull: false
        },
        grade: {
            type: DataTypes.INTEGER,
            allowNull: false
        },
        contents: {
            type: DataTypes.STRING(6000),
            allowNull: false
        },
        time: {
            type: DataTypes.STRING(45),
            allowNull: false
        },
        user_id: {
            type: DataTypes.STRING(10),
            allowNull: false
        }
        
    },{
        timestamps: false,
        tableName: 'chat'
    })
}