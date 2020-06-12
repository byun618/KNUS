module.exports = (sequelize, DataTypes) => {
    return sequelize.define('chat', {
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
        }
        
    },{
        timestamps: false,
        tableName: 'chat'
    })
}